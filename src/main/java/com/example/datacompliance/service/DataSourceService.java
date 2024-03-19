package com.example.datacompliance.service;

import com.example.datacompliance.entity.DataSourceConfig;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.sql.SQLException;

public interface DataSourceService {
    void newConnect(DataSourceConfig dataSourceConfig) throws SQLException, ClassNotFoundException, JsonProcessingException;

    DataSourceConfig findConfigById(Integer id);
}
