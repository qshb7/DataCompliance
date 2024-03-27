package com.example.datacompliance.mapper;

import com.example.datacompliance.entity.LoginFailureLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LoginFailureLogMapper {
    @Insert("INSERT INTO login_failure_log (timestamp, account_user, account_host, login_ip, datasource_id) " +
            "VALUES (#{timestamp}, #{accountUser}, #{accountHost}, #{loginIp}, #{datasourceId})")
    void insert(LoginFailureLog log);

    @Select("SELECT * FROM login_failure_log")
    List<LoginFailureLog> findAll();

}
