package com.example.datacompliance.service;

import com.example.datacompliance.entity.UnauthorizedAccessLog;

import java.util.List;

public interface UnauthorizedAccessLogService {
    void insert(UnauthorizedAccessLog log);
    List<UnauthorizedAccessLog> findAll();
}
