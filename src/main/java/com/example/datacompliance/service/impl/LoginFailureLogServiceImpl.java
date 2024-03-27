package com.example.datacompliance.service.impl;

import com.example.datacompliance.entity.LoginFailureLog;
import com.example.datacompliance.mapper.LoginFailureLogMapper;
import com.example.datacompliance.service.LoginFailureLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginFailureLogServiceImpl implements LoginFailureLogService {

    @Autowired
    private LoginFailureLogMapper loginFailureLogMapper;

    @Override
    public void insert(LoginFailureLog log) {
        loginFailureLogMapper.insert(log);
    }

    @Override
    public List<LoginFailureLog> findAll() {
        return loginFailureLogMapper.findAll();
    }

    // Other methods for business logic
}