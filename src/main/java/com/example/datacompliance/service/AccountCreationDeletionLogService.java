package com.example.datacompliance.service;

import com.example.datacompliance.entity.AccountCreationDeletionLog;

import java.util.List;

public interface AccountCreationDeletionLogService {
    void insert(AccountCreationDeletionLog log);
    List<AccountCreationDeletionLog> findAll();
}
