package com.example.datacompliance.service;

import com.example.datacompliance.entity.LoginFailureLog;

import java.util.List;

public interface LoginFailureLogService {
    void insert(LoginFailureLog log);
    List<LoginFailureLog> findAll();
}
