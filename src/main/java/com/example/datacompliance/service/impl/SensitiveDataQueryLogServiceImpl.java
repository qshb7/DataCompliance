package com.example.datacompliance.service.impl;

import com.example.datacompliance.entity.SensitiveDataQueryLog;
import com.example.datacompliance.mapper.SensitiveDataQueryLogMapper;
import com.example.datacompliance.service.SensitiveDataQueryLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensitiveDataQueryLogServiceImpl implements SensitiveDataQueryLogService {

    @Autowired
    private SensitiveDataQueryLogMapper sensitiveDataQueryLogMapper;

    @Override
    public void insert(SensitiveDataQueryLog log) {
        sensitiveDataQueryLogMapper.insert(log);
    }

    @Override
    public List<SensitiveDataQueryLog> findAll() {
        return sensitiveDataQueryLogMapper.findAll();
    }

    // Other methods for business logic
}
