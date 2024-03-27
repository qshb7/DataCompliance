package com.example.datacompliance.entity;

import lombok.Data;

@Data
public class AuditLog {
    private Integer id;
    private Integer datasourceId;
    private String timestamp;
    private String className;
    private String event;
    private String connectionId;
    private String accountUser;
    private String accountHost;
    private String db;
    private String table;
    private String loginIp;
    private Integer status;
    private String sqlCommand;
    private String query;
}
