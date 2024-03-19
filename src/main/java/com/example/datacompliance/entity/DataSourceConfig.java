package com.example.datacompliance.entity;

import lombok.Data;

@Data
public class DataSourceConfig {
    private String dataSourceType;
    private String name;
    private String description;
    private String ip;
    private String port;
    private String userName;
    private String passwd;
    private String database;
    private String config;
}
