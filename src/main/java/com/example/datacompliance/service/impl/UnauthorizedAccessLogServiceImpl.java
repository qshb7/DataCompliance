package com.example.datacompliance.service.impl;

import com.example.datacompliance.entity.UnauthorizedAccessLog;
import com.example.datacompliance.mapper.UnauthorizedAccessLogMapper;
import com.example.datacompliance.service.UnauthorizedAccessLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnauthorizedAccessLogServiceImpl implements UnauthorizedAccessLogService {

    @Autowired
    private UnauthorizedAccessLogMapper unauthorizedAccessLogMapper;

    @Override
    public void insert(UnauthorizedAccessLog log) {
        unauthorizedAccessLogMapper.insert(log);
    }

    @Override
    public List<UnauthorizedAccessLog> findAll() {
        return unauthorizedAccessLogMapper.findAll();
    }

    // Other methods for business logic
}