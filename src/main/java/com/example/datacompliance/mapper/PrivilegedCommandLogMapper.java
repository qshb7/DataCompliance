package com.example.datacompliance.mapper;

import com.example.datacompliance.entity.PrivilegedCommandLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PrivilegedCommandLogMapper {
    @Insert("INSERT INTO privileged_command_execution_log (timestamp, account_user, account_host, login_ip, sql_command, query, datasource_id) " +
            "VALUES (#{timestamp}, #{accountUser}, #{accountHost}, #{loginIp}, #{sqlCommand}, #{query}, #{datasourceId})")
    void insert(PrivilegedCommandLog log);

    @Select("SELECT * FROM privileged_command_execution_log")
    List<PrivilegedCommandLog> findAll();
}
