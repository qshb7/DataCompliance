package com.example.datacompliance.service;

import com.example.datacompliance.entity.PrivilegedCommandLog;

import java.util.List;

public interface PrivilegedCommandLogService {
    void insert(PrivilegedCommandLog log);
    List<PrivilegedCommandLog> findAll();
}
