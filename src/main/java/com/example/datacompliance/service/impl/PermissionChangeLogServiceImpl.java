package com.example.datacompliance.service.impl;

import com.example.datacompliance.entity.PermissionChangeLog;
import com.example.datacompliance.mapper.PermissionChangeLogMapper;
import com.example.datacompliance.service.PermissionChangeLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionChangeLogServiceImpl implements PermissionChangeLogService {

    @Autowired
    private PermissionChangeLogMapper permissionChangeLogMapper;

    @Override
    public void insert(PermissionChangeLog log) {
        permissionChangeLogMapper.insert(log);
    }

    @Override
    public List<PermissionChangeLog> findAll() {
        return permissionChangeLogMapper.findAll();
    }

    // Other methods for business logic
}
