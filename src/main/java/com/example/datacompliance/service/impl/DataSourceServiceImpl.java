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
            Statement statement = connection.createStatement();
            // 执行查询语句
            ResultSet resultSet = statement.executeQuery("SHOW TABLES");

            // 遍历结果集并输出表名
            System.out.println("Tables in the database:");
            while (resultSet.next()) {
                String tableName = resultSet.getString(1); // 第一列的表名
                System.out.println(tableName);
            }

            // 释放资源
            resultSet.close();
            statement.close();
            connection.close();

        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
