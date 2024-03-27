package com.example.datacompliance.mapper;

import com.example.datacompliance.entity.SensitiveDataQueryLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SensitiveDataQueryLogMapper {
    @Insert("INSERT INTO sensitive_data_query_log (timestamp, account_user, account_host, login_ip, db, table_name, sql_command, query, datasource_id) " +
            "VALUES (#{timestamp}, #{accountUser}, #{accountHost}, #{loginIp}, #{db}, #{tableName}, #{sqlCommand}, #{query}, #{datasourceId})")
    void insert(SensitiveDataQueryLog log);

    @Select("SELECT * FROM sensitive_data_query_log")
    List<SensitiveDataQueryLog> findAll();

    // Other methods for CRUD operations
}
