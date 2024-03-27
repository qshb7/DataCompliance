package com.example.datacompliance.service.impl;

import com.example.datacompliance.entity.PrivilegedCommandLog;
import com.example.datacompliance.mapper.PrivilegedCommandLogMapper;
import com.example.datacompliance.service.PrivilegedCommandLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrivilegedCommandLogServiceImpl implements PrivilegedCommandLogService {

    @Autowired
    private PrivilegedCommandLogMapper privilegedCommandLogMapper;

    @Override
    public void insert(PrivilegedCommandLog log) {
        privilegedCommandLogMapper.insert(log);
    }

    @Override
    public List<PrivilegedCommandLog> findAll() {
        return privilegedCommandLogMapper.findAll();
    }

    // Other methods for business logic
}
