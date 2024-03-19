package com.example.datacompliance.service.impl;

import com.example.datacompliance.entity.DataSourceConfig;
import com.example.datacompliance.mapper.DataSourceConfigMapper;
import com.example.datacompliance.service.DataSourceService;
import com.example.datacompliance.utils.DataSourceUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;

@Service
public class DataSourceServiceImpl implements DataSourceService {
    @Autowired
    DataSourceConfigMapper dataSourceConfigMapper;
    @Override
    public void newConnect(DataSourceConfig dataSourceConfig) throws SQLException, ClassNotFoundException, JsonProcessingException {
        try {
            Connection connection = DataSourceUtil.connect(dataSourceConfig);
            dataSourceConfigMapper.newConnect(dataSourceConfig.getDataSourceType(),dataSourceConfig.getName(),dataSourceConfig.getDescription(),
                    dataSourceConfig.getIp(),dataSourceConfig.getPort(),dataSourceConfig.getUserName(),dataSourceConfig.getPasswd(),
                    dataSourceConfig.getDatabase(),dataSourceConfig.getConfig());

            // 执行查询
            System.out.println(DataSourceUtil.getAllTables(connection));
            // 释放资源
            connection.close();
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public DataSourceConfig findConfigById(Integer id) {
        return dataSourceConfigMapper.findConfigById(id);
    }
}
