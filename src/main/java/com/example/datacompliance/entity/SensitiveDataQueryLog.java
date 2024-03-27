package com.example.datacompliance.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SensitiveDataQueryLog {
    private Integer id;
    private LocalDateTime timestamp;
    private String accountUser;
    private String accountHost;
    private String loginIp;
    private String db;
    private String tableName;
    private String sqlCommand;
    private String query;
    private Integer datasourceId;

}
