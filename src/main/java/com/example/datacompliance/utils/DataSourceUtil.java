package com.example.datacompliance.utils;

import com.example.datacompliance.entity.DataSourceConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
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
}
