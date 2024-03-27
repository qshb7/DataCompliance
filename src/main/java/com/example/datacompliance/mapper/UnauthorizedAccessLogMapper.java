package com.example.datacompliance.mapper;

import com.example.datacompliance.entity.UnauthorizedAccessLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UnauthorizedAccessLogMapper {
    @Insert("INSERT INTO unauthorized_access_log (timestamp, account_user, account_host, login_ip, sql_command, query, datasource_id) " +
            "VALUES (#{timestamp}, #{accountUser}, #{accountHost}, #{loginIp}, #{sqlCommand}, #{query}, #{datasourceId})")
    void insert(UnauthorizedAccessLog log);

    @Select("SELECT * FROM unauthorized_access_log")
    List<UnauthorizedAccessLog> findAll();
}
