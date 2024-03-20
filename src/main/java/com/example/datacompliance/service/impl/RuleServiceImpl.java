package com.example.datacompliance.service.impl;

import com.example.datacompliance.entity.*;
import com.example.datacompliance.mapper.*;
import com.example.datacompliance.service.RuleService;
import com.example.datacompliance.utils.DataSourceUtil;
import com.example.datacompliance.utils.JsonUtil;
import com.example.datacompliance.utils.VerifyUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class RuleServiceImpl implements RuleService {
    @Autowired
    RuleMapper ruleMapper;

    @Autowired
    DataSourceConfigMapper dataSourceConfigMapper;

    @Autowired
    SensitiveDataMapper sensitiveDataMapper;

    @Autowired
    TaskMapper taskMapper;

    @Autowired
    TemplateRuleMapper templateRuleMapper;

    @Override
    public void addRule(Rule rule) {
        ruleMapper.addRule(rule.getName(), rule.getDescription(), rule.getMatchOperators(), rule.getSource(), rule.getStatus(), rule.getContentRule(), rule.getMetaRule());
        System.out.println(rule);
    }

    @Override
    public int verifyRule(VerifyRule verifyRule) throws JsonProcessingException {
        String matchOperators = verifyRule.getMatchOperators();
        String verifyContent = verifyRule.getVerifyContent();
        String verifyMeta = verifyRule.getVerifyMeta();
        // 创建 ObjectMapper 实例
        ObjectMapper objectMapper = new ObjectMapper();
        RuleDetail contentRule = objectMapper.readValue(verifyRule.getContentRule(), RuleDetail.class);
        RuleDetail metaRule = objectMapper.readValue(verifyRule.getMetaRule(), RuleDetail.class);

        int contentResult = VerifyUtil.verifyRuleContent(contentRule, verifyContent);
        int metaResult = VerifyUtil.verifyRuleContent(metaRule, verifyMeta);
        if (matchOperators.equals("or")) {
            if (contentResult == 1 || metaResult == 1) {
                return 1;
            }
        } else {
            if (contentResult == 1 && metaResult == 1) {
                return 1;
            }
        }
        return 0;
    }

    @Override
    public void deleteRule(Integer id) {
        ruleMapper.deleteRule(id);
    }

    @Override
    public void updateRule(Rule rule) {
        Integer id = rule.getId();
        String name = rule.getName();
        String description = rule.getDescription();
        String matchOperators = rule.getMatchOperators();
        Integer status = rule.getStatus();
        String contentRule = rule.getContentRule();
        String metaRule = rule.getMetaRule();
        ruleMapper.updateRule(id, name, description, matchOperators, status, contentRule, metaRule);
    }

    @Override
    public List<Rule> findRuleAll() {
        System.out.println(ruleMapper.findRuleAll());
        return ruleMapper.findRuleAll();
    }

    @Override
    public Rule findRuleByName(String name) {
        return ruleMapper.findRuleByName(name);
    }

    @Override
    public void newScanTask(TaskParams taskParams) {
        DataSourceConfig dataSourceConfig = dataSourceConfigMapper.findConfigById(taskParams.getDataSourceId());
        List<Map<String, Object>> ruleList = templateRuleMapper.getRulesByTemplateId(taskParams.getTemplateId());

        Task task=new Task();
        task.setName(taskParams.getName());
        task.setDescription(taskParams.getDescription());
        task.setDataSourceId(taskParams.getDataSourceId());
        task.setTemplateId(taskParams.getTemplateId());
        task.setCycle(taskParams.getCycle());
        task.setExecutePlan(taskParams.getExecutePlan());
        task.setStartTime(taskParams.getStartTime());
        task.setStatus("正在扫描");
        taskMapper.addTask(task);
        Connection connection = null;
        try {
            // 获取数据库连接
            connection = DataSourceUtil.connect(dataSourceConfig);
            // 获取数据库中所有表的列表
            List<String> tables = DataSourceUtil.getAllTables(connection);
            //遍历所有表
            for (String table : tables) {
                // 获取表中所有字段的列表
                List<String> fields = DataSourceUtil.getAllFields(connection, table);
                for (String field : fields) {
                    Object result = DataSourceUtil.getFieldValue(connection, table, field);
                    String field_value = result != null ? result.toString() : "";
                    //遍历规则去匹配
                    for (Map<String, Object> rule : ruleList) {
                        String ruleName = (String) rule.get("rule_name");
                        String ruleRank = (String) rule.get("rule_rank");
                        Rule ruleDetail = findRuleByName(ruleName);
                        VerifyRule verifyRule = new VerifyRule();
                        verifyRule.setContentRule(ruleDetail.getContentRule());
                        verifyRule.setMetaRule(ruleDetail.getMetaRule());
                        verifyRule.setMatchOperators(ruleDetail.getMatchOperators());
                        verifyRule.setVerifyContent(field_value);
                        verifyRule.setVerifyMeta(field);
                        int matchResult = verifyRule(verifyRule);
                        if (matchResult == 1) {
                            //匹配成功
                            sensitiveDataMapper.addSensitiveData(taskParams.getDataSourceId(), dataSourceConfig.getDatabase(), table, field, ruleDetail.getId(), ruleRank);
                        }
                    }

                }

            }

            // 从数据库中获取刚插入的任务的ID
            Integer taskId = taskMapper.getLastInsertedTaskId();
            if (taskId != null) {
                // 设置任务状态为已完成
                task.setId(taskId); // 设置任务ID
                task.setStatus("已完成");
                task.setLastFinishedTime(LocalDateTime.now());
                taskMapper.updateTask(task);
            } else {
                // 处理未能获取到任务ID的情况
                System.err.println("Failed to get last inserted task ID.");
            }


        } catch (SQLException | JsonProcessingException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
