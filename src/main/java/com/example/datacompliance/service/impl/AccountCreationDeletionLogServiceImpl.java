package com.example.datacompliance.service.impl;

import com.example.datacompliance.entity.AccountCreationDeletionLog;
import com.example.datacompliance.mapper.AccountCreationDeletionLogMapper;
import com.example.datacompliance.service.AccountCreationDeletionLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountCreationDeletionLogServiceImpl implements AccountCreationDeletionLogService {

    @Autowired
    private AccountCreationDeletionLogMapper accountCreationDeletionLogMapper;

    @Override
    public void insert(AccountCreationDeletionLog log) {
        accountCreationDeletionLogMapper.insert(log);
    }

    @Override
    public List<AccountCreationDeletionLog> findAll() {
        return accountCreationDeletionLogMapper.findAll();
    }

    // Other methods for business logic
}
