package com.example.datacompliance.service;

import com.example.datacompliance.entity.SensitiveDataQueryLog;

import java.util.List;

public interface SensitiveDataQueryLogService {
    void insert(SensitiveDataQueryLog log);
    List<SensitiveDataQueryLog> findAll();
}
