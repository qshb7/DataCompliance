package com.example.datacompliance.service.impl;

import com.example.datacompliance.entity.*;
import com.example.datacompliance.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AuditAnalysisServiceImpl implements AuditAnalysisService {
    @Autowired
    private RiskService riskService;

    @Autowired
    private AuditLogService auditLogService;

    @Autowired
    private SensitiveDataService sensitiveDataService;

    @Autowired
    private PrivilegedCommandLogService privilegedCommandLogService;

    @Autowired
    private SensitiveDataQueryLogService sensitiveDataQueryLogService;

    @Autowired
    private AccountCreationDeletionLogService accountCreationDeletionLogService;

    @Autowired
    private PermissionChangeLogService permissionChangeLogService;

    @Autowired
    private LoginFailureLogService loginFailureLogService;

    @Autowired
    private UnauthorizedAccessLogService unauthorizedAccessLogService;

    public void analyzeAuditLogsAndSaveRisk() {
        List<AuditLog> auditLogs = auditLogService.fetchAuditLogs();
        analyzePrivilegedCommandExecution(auditLogs);
        analyzeSensitiveDataQueries(auditLogs);
        analyzeAccountCreationDeletion(auditLogs);
        analyzePermissionChanges(auditLogs);
        analyzeUnauthorizedAccess(auditLogs);
        analyzeFailedLoginAttempts(auditLogs);
    }

    private void analyzePrivilegedCommandExecution(List<AuditLog> auditLogs) {
        // 分析用户特权命令执行情况的逻辑
        List<Risk> risks = new ArrayList<>();

        for (AuditLog log : auditLogs) {
            if ("general".equals(log.getClassName()) && "status".equals(log.getEvent())) {
                String sqlCommand = log.getSqlCommand();
                if (sqlCommand != null && sqlCommand.contains("SET") || sqlCommand.contains("GRANT") || sqlCommand.contains("REVOKE")) {
                    // 如果存在特权命令执行记录，则记录到风险表中
                    Risk risk = new Risk();
                    risk.setType("Privileged Command Execution");
                    risk.setDescription("Privileged command executed by user " + log.getAccountUser() + " at " + log.getTimestamp());
                    risks.add(risk);

                    // 保存到特权命令执行记录表中
                    PrivilegedCommandLog commandLog = new PrivilegedCommandLog();
                    commandLog.setTimestamp(LocalDateTime.parse(log.getTimestamp()));
                    commandLog.setAccountUser(log.getAccountUser());
                    commandLog.setAccountHost(log.getAccountHost());
                    commandLog.setLoginIp(log.getLoginIp());
                    commandLog.setSqlCommand(log.getSqlCommand());
                    commandLog.setQuery(log.getQuery());
                    commandLog.setDatasourceId(log.getDatasourceId()); // 设置数据源 ID
                    privilegedCommandLogService.insert(commandLog);
                }
            }
        }

        // 保存风险记录到风险表
        if (!risks.isEmpty()) {
            riskService.saveRisks(risks);
        }
    }

    private void analyzeSensitiveDataQueries(List<AuditLog> auditLogs) {
        // 查询敏感数据表
        List<String> sensitiveTables = sensitiveDataService.findSensitiveTables(1); // 假设数据源 ID 为 1

        // 计算每个敏感数据表的查询次数
        Map<String, Integer> queryCounts = new HashMap<>();
        for (String tableName : sensitiveTables) {
            int count = 0;
            for (AuditLog log : auditLogs) {
                if ("table_access_data".equals(log.getClassName()) && "read".equals(log.getEvent()) && tableName.equals(log.getTable())) {
                    count++;
                }
            }
            queryCounts.put(tableName, count);
        }

        // 对查询次数进行排序
        List<Map.Entry<String, Integer>> sortedQueryCounts = new ArrayList<>(queryCounts.entrySet());
        sortedQueryCounts.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

        List<Risk> risks = new ArrayList<>();

        // 获取前几个查询次数最高的敏感数据表
        int topN = 5; // 假设获取前5个查询次数最高的敏感数据表
        for (int i = 0; i < Math.min(topN, sortedQueryCounts.size()); i++) {
            String tableName = sortedQueryCounts.get(i).getKey();
            int queryCount = sortedQueryCounts.get(i).getValue();

            // 将查询次数较高的敏感数据表记录到风险表中
            Risk risk = new Risk();
            risk.setType("Sensitive Data Query Ranking");
            risk.setDescription("High query count (" + queryCount + ") for table " + tableName);
            risks.add(risk);
        }

        // 保存风险记录到风险表
        if (!risks.isEmpty()) {
            riskService.saveRisks(risks);
        }

        // 保存到相应的数据表中
        for (AuditLog log : auditLogs) {
            if ("table_access_data".equals(log.getClassName()) && "read".equals(log.getEvent())) {
                String tableName = log.getTable();

                if (queryCounts.containsKey(tableName) && queryCounts.get(tableName) > 0) {
                    SensitiveDataQueryLog sensitiveDataQueryLog = new SensitiveDataQueryLog();
                    sensitiveDataQueryLog.setTimestamp(LocalDateTime.parse(log.getTimestamp()));
                    sensitiveDataQueryLog.setAccountUser(log.getAccountUser());
                    sensitiveDataQueryLog.setAccountHost(log.getAccountHost());
                    sensitiveDataQueryLog.setLoginIp(log.getLoginIp());
                    sensitiveDataQueryLog.setDb(log.getDb());
                    sensitiveDataQueryLog.setTableName(tableName);
                    sensitiveDataQueryLog.setSqlCommand(log.getSqlCommand());
                    sensitiveDataQueryLog.setQuery(log.getQuery());
                    sensitiveDataQueryLog.setDatasourceId(log.getDatasourceId());

                    sensitiveDataQueryLogService.insert(sensitiveDataQueryLog);
                }
            }
        }
    }


    private void analyzeAccountCreationDeletion(List<AuditLog> auditLogs) {
        // 分析账号创建删除记录的逻辑
        List<Risk> risks = new ArrayList<>();

        for (AuditLog log : auditLogs) {
            if ("audit".equals(log.getClassName()) && ("startup".equals(log.getEvent()) || "shutdown".equals(log.getEvent()))) {
                // 检查是否存在账号的创建或删除操作
                if ("startup".equals(log.getEvent())) {
                    Risk risk = new Risk();
                    risk.setType("Account Creation");
                    risk.setDescription("User account created at " + log.getTimestamp());
                    risks.add(risk);

                    AccountCreationDeletionLog accountLog = new AccountCreationDeletionLog();
                    accountLog.setTimestamp(LocalDateTime.parse(log.getTimestamp()));
                    accountLog.setAccountUser(log.getAccountUser());
                    accountLog.setAccountHost(log.getAccountHost());
                    accountLog.setLoginIp(log.getLoginIp());
                    accountLog.setSqlCommand(log.getSqlCommand());
                    accountLog.setQuery(log.getQuery());
                    accountLog.setDatasourceId(log.getDatasourceId());
                    accountCreationDeletionLogService.insert(accountLog);
                } else if ("shutdown".equals(log.getEvent())) {
                    Risk risk = new Risk();
                    risk.setType("Account Deletion");
                    risk.setDescription("User account deleted at " + log.getTimestamp());
                    risks.add(risk);

                    // 保存到相应的数据表中
                    AccountCreationDeletionLog accountLog = new AccountCreationDeletionLog();
                    accountLog.setTimestamp(LocalDateTime.parse(log.getTimestamp()));
                    accountLog.setAccountUser(log.getAccountUser());
                    accountLog.setAccountHost(log.getAccountHost());
                    accountLog.setLoginIp(log.getLoginIp());
                    accountLog.setSqlCommand(log.getSqlCommand());
                    accountLog.setQuery(log.getQuery());
                    accountLog.setDatasourceId(log.getDatasourceId());
                    accountCreationDeletionLogService.insert(accountLog);
                }
            }
        }

        // 保存风险记录到风险表
        if (!risks.isEmpty()) {
            riskService.saveRisks(risks);
        }
    }

    private void analyzePermissionChanges(List<AuditLog> auditLogs) {
        // 分析权限变更记录的逻辑
        List<Risk> risks = new ArrayList<>();

        for (AuditLog log : auditLogs) {
            if ("table_access_data".equals(log.getClassName()) && ("insert".equals(log.getEvent()) || "update".equals(log.getEvent()) || "delete".equals(log.getEvent()))) {
                // 检查是否存在权限的变更操作
                String sqlCommand = log.getSqlCommand();
                if (sqlCommand != null && (sqlCommand.toUpperCase().contains("GRANT") || sqlCommand.toUpperCase().contains("REVOKE"))) {
                    // 执行了 GRANT 或 REVOKE 命令
                    Risk risk = new Risk();
                    risk.setType("Permission Change");
                    risk.setDescription("Permission changed at " + log.getTimestamp() + " by user " + log.getAccountUser());
                    risks.add(risk);

                    // 保存到相应的数据表中
                    PermissionChangeLog permissionChangeLog = new PermissionChangeLog();
                    permissionChangeLog.setTimestamp(LocalDateTime.parse(log.getTimestamp()));
                    permissionChangeLog.setAccountUser(log.getAccountUser());
                    permissionChangeLog.setAccountHost(log.getAccountHost());
                    permissionChangeLog.setLoginIp(log.getLoginIp());
                    permissionChangeLog.setSqlCommand(log.getSqlCommand());
                    permissionChangeLog.setQuery(log.getQuery());
                    permissionChangeLog.setDatasourceId(log.getDatasourceId());
                    permissionChangeLogService.insert(permissionChangeLog);
                }
            }
        }

        // 保存风险记录到风险表
        if (!risks.isEmpty()) {
            riskService.saveRisks(risks);
        }
    }

    private void analyzeUnauthorizedAccess(List<AuditLog> auditLogs) {
        // 分析未授权访问记录的逻辑
        List<Risk> risks = new ArrayList<>();

        for (AuditLog log : auditLogs) {
            if ("connection".equals(log.getClassName()) && ("connect".equals(log.getEvent()) || "disconnect".equals(log.getEvent()))) {
                // 检查是否存在未授权的连接操作
                if ("connect".equals(log.getEvent())) {
                    // 未授权连接操作
                    Risk risk = new Risk();
                    risk.setType("Unauthorized Access");
                    risk.setDescription("Unauthorized connection attempt detected at " + log.getTimestamp() + " from " + log.getLoginIp());
                    risks.add(risk);

                    // 保存到相应的数据表中
                    UnauthorizedAccessLog unauthorizedAccessLog = new UnauthorizedAccessLog();
                    unauthorizedAccessLog.setTimestamp(LocalDateTime.parse(log.getTimestamp()));
                    unauthorizedAccessLog.setAccountUser(log.getAccountUser());
                    unauthorizedAccessLog.setAccountHost(log.getAccountHost());
                    unauthorizedAccessLog.setLoginIp(log.getLoginIp());
                    unauthorizedAccessLog.setSqlCommand(log.getSqlCommand());
                    unauthorizedAccessLog.setQuery(log.getQuery());
                    unauthorizedAccessLog.setDatasourceId(log.getDatasourceId());
                    unauthorizedAccessLogService.insert(unauthorizedAccessLog);
                } else if ("disconnect".equals(log.getEvent())) {
                    // 断开连接操作
                    // 这里可以根据具体需求进行处理
                }
            }
        }

        // 保存风险记录到风险表
        if (!risks.isEmpty()) {
            riskService.saveRisks(risks);
        }
    }

    private void analyzeFailedLoginAttempts(List<AuditLog> auditLogs) {
        Map<String, Integer> loginFailures = new HashMap<>();

        for (AuditLog log : auditLogs) {
            if ("connection".equals(log.getClassName()) && "connect".equals(log.getEvent()) && log.getStatus() != 0) {
                String userAndHost = log.getAccountUser() + "@" + log.getAccountHost();
                loginFailures.put(userAndHost, loginFailures.getOrDefault(userAndHost, 0) + 1);
            }
        }

        List<Risk> risks = new ArrayList<>();
        int failureThreshold = 3;

        for (AuditLog log : auditLogs) {
            if ("connection".equals(log.getClassName()) && "connect".equals(log.getEvent()) && log.getStatus() != 0) {
                String userAndHost = log.getAccountUser() + "@" + log.getAccountHost();
                int failureCount = loginFailures.getOrDefault(userAndHost, 0);
                if (failureCount > failureThreshold) {
                    Risk risk = new Risk();
                    risk.setType("Login Failure");
                    risk.setDescription("Excessive login failures detected for user " + userAndHost + ". Count: " + failureCount);
                    risks.add(risk);

                    // 保存到相应的数据表中
                    LoginFailureLog loginFailureLog = new LoginFailureLog();
                    loginFailureLog.setTimestamp(LocalDateTime.parse(log.getTimestamp()));
                    loginFailureLog.setAccountUser(log.getAccountUser());
                    loginFailureLog.setAccountHost(log.getAccountHost());
                    loginFailureLog.setLoginIp(log.getLoginIp());
                    loginFailureLog.setDatasourceId(log.getDatasourceId());
                    loginFailureLogService.insert(loginFailureLog);
                }
            }
        }

        if (!risks.isEmpty()) {
            riskService.saveRisks(risks);
        }
    }
}
