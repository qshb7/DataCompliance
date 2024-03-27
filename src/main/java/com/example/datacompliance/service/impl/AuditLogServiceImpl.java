package com.example.datacompliance.service.impl;

import com.example.datacompliance.entity.AuditLog;
import com.example.datacompliance.mapper.AuditLogMapper;
import com.example.datacompliance.service.AuditLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditLogServiceImpl implements AuditLogService {
    @Autowired
    private AuditLogMapper auditLogMapper;

    @Override
    public List<AuditLog> fetchAuditLogs() {
        // 查询数据库中的日志记录并返回
        return auditLogMapper.selectAllAuditLogs();
    }
}
