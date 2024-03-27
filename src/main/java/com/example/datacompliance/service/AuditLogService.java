package com.example.datacompliance.service;

import com.example.datacompliance.entity.AuditLog;

import java.util.List;

public interface AuditLogService {
    // 获取数据库中的日志记录
    List<AuditLog> fetchAuditLogs();
}
