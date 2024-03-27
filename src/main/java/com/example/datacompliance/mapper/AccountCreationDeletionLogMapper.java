package com.example.datacompliance.mapper;

import com.example.datacompliance.entity.AccountCreationDeletionLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AccountCreationDeletionLogMapper {
    @Insert("INSERT INTO account_creation_deletion_log (timestamp, account_user, account_host, login_ip, sql_command, query, datasource_id) " +
            "VALUES (#{timestamp}, #{accountUser}, #{accountHost}, #{loginIp}, #{sqlCommand}, #{query}, #{datasourceId})")
    void insert(AccountCreationDeletionLog log);

    @Select("SELECT * FROM account_creation_deletion_log")
    List<AccountCreationDeletionLog> findAll();
}
