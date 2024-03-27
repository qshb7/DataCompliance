package com.example.datacompliance.mapper;

import com.example.datacompliance.entity.PermissionChangeLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PermissionChangeLogMapper {
    @Insert("INSERT INTO permission_change_log (timestamp, account_user, account_host, login_ip, sql_command, query, datasource_id) " +
            "VALUES (#{timestamp}, #{accountUser}, #{accountHost}, #{loginIp}, #{sqlCommand}, #{query}, #{datasourceId})")
    void insert(PermissionChangeLog log);

    @Select("SELECT * FROM permission_change_log")
    List<PermissionChangeLog> findAll();

    // Other methods for CRUD operations
}
