package com.example.datacompliance.utils;

import com.example.datacompliance.entity.DataSourceConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DataSourceUtil {
    public static Connection connect(DataSourceConfig config) throws SQLException, JsonProcessingException {
        String url;
        String driverClassName;

        // 根据配置对象中的数据源类型选择不同的驱动程序和连接方式
        switch (config.getDataSourceType()) {
            case "mysql":
                url = "jdbc:mysql://" + config.getIp() + ":" + config.getPort() + "/" + config.getDatabase();
                driverClassName = "com.mysql.cj.jdbc.Driver";
                break;
            case "postgresql":
                url = "jdbc:postgresql://" + config.getIp() + ":" + config.getPort() + "/" + config.getDatabase();
                driverClassName = "org.postgresql.Driver";
                break;
            case "oracle":
                url = "jdbc:oracle:thin:@" + config.getIp() + ":" + config.getPort() + ":" + config.getDatabase();
                driverClassName = "oracle.jdbc.driver.OracleDriver";
                break;
            default:
                throw new IllegalArgumentException("Unsupported dataSourceType: " + config.getDataSourceType());
        }

        // 添加配置项到 URL 的查询字符串中
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode configNode = objectMapper.readTree(config.getConfig());
        StringBuilder queryString = new StringBuilder();
        for (Iterator<Map.Entry<String, JsonNode>> it = configNode.fields(); it.hasNext(); ) {
            Map.Entry<String, JsonNode> entry = it.next();
            if (queryString.length() > 0) {
                queryString.append("&");
            }
            queryString.append(entry.getKey()).append("=").append(entry.getValue().asText());
        }

        url += "?" + queryString;
        System.out.println(url);

        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(url);
        hikariConfig.setUsername(config.getUserName());
        hikariConfig.setPassword(config.getPasswd());
        hikariConfig.setDriverClassName(driverClassName);

        // 添加其他连接池参数，例如连接池大小、连接超时时间等
        // hikariConfig.setMaximumPoolSize(10);
        // hikariConfig.setConnectionTimeout(30000);


        // 创建 HikariDataSource 实例
        HikariDataSource dataSource = new HikariDataSource(hikariConfig);

        // 获取连接
        return dataSource.getConnection();
    }

    public static List<String> getAllTables(Connection connection) throws SQLException {
        // 执行获取所有表的 SQL 查询，返回表名列表
        List<String> tables = new ArrayList<>();
        DatabaseMetaData metaData = connection.getMetaData();
        try (ResultSet resultSet = metaData.getTables(null, null, "%", new String[]{"TABLE"})) {
            while (resultSet.next()) {
                String tableName = resultSet.getString("TABLE_NAME");
                tables.add(tableName);
            }
        }
        return tables;
    }

    public static List<String> getAllFields(Connection connection, String tableName) throws SQLException {
        List<String> fields = new ArrayList<>();
        DatabaseMetaData metaData = connection.getMetaData();
        try (ResultSet resultSet = metaData.getColumns(null, null, tableName, null)) {
            while (resultSet.next()) {
                String fieldName = resultSet.getString("COLUMN_NAME");
                fields.add(fieldName);
            }
        }
        return fields;
    }

    public static Object getFieldValue(Connection connection, String tableName, String fieldName) throws SQLException {
        Object value = null;
        String sql = "SELECT `" + fieldName + "` FROM `" + tableName + "` LIMIT 1";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            if (resultSet.next()) {
                value = resultSet.getObject(fieldName);
            }
        }
        return value;
    }




}
