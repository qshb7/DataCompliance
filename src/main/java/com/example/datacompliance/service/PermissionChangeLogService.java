package com.example.datacompliance.service;

import com.example.datacompliance.entity.PermissionChangeLog;

import java.util.List;

public interface PermissionChangeLogService {
    void insert(PermissionChangeLog log);
    List<PermissionChangeLog> findAll();
}
