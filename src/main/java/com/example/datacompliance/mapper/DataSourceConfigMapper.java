package com.example.datacompliance.mapper;

import com.example.datacompliance.entity.DataSourceConfig;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DataSourceConfigMapper {
    @Insert("INSERT INTO data_source_config (data_source_type, name, description, ip, port, userName, passwd, `database`, config) " +
            "VALUES (#{dataSourceType}, #{name}, #{description}, #{ip}, #{port}, #{userName}, #{passwd}, #{database}, #{config})")
    void newConnect(String dataSourceType,String name,String description,String ip,String port,String userName,String passwd,String database,String config);

    @Select("select * from data_source_config where id=#{id}")
    DataSourceConfig findConfigById(Integer id);
}
