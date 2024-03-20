package com.example.datacompliance.mapper;

import com.example.datacompliance.entity.SensitiveData;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SensitiveDataMapper {
    @Insert("insert into sensitive_data (data_source_id, database_name, table_name, field_name, rule_id,rule_rank) values (#{data_source_id}, #{database_name}, #{table_name}, #{field_name}, #{rule_id},#{rule_rank})")
    void addSensitiveData(Integer data_source_id,String database_name, String table_name, String field_name, Integer rule_id,String rule_rank);

    @Delete("DELETE FROM sensitive_data WHERE id = #{id}")
    void deleteSensitiveDataById(Integer id);

    @Update("UPDATE sensitive_data SET database_name = #{databaseName}, table_name = #{tableName}, field_name = #{fieldName}, rule_id = #{ruleId}, rule_rank = #{ruleRank} WHERE id = #{id}")
    void updateSensitiveDataById(Integer id, String databaseName, String tableName, String fieldName, Integer ruleId, String ruleRank);

    @Select("SELECT * FROM sensitive_data WHERE id = #{id}")
    SensitiveData findSensitiveDataById(Integer id);

    @Select("SELECT * FROM sensitive_data WHERE data_source_id = #{dataSourceId}")
    List<SensitiveData> findSensitiveDataByDataSourceId(Integer dataSourceId);

    @Select("SELECT DISTINCT data_source_id, table_name, field_name FROM sensitive_data")
    List<SensitiveData> findDistinctDataAndFields();



}
