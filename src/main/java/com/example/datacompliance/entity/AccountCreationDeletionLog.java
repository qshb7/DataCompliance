package com.example.datacompliance.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AccountCreationDeletionLog {
    private Integer id;
    private LocalDateTime timestamp;
    private String accountUser;
    private String accountHost;
    private String loginIp;
    private String sqlCommand;
    private String query;
    private Integer datasourceId;
}
