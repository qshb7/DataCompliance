package com.example.datacompliance.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SensitiveDataMapper {
    @Insert("insert into sensitive_data (data_source_id, database_name, table_name, field_name, rule_id,rule_rank) values (#{data_source_id}, #{database_name}, #{table_name}, #{field_name}, #{rule_id},#{rule_rank})")
    void addSensitiveData(Integer data_source_id,String database_name, String table_name, String field_name, Integer rule_id,String rule_rank);

}
