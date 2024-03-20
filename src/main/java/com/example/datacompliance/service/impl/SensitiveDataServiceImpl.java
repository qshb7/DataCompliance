package com.example.datacompliance.service.impl;

import com.example.datacompliance.entity.SensitiveData;
import com.example.datacompliance.mapper.SensitiveDataMapper;
import com.example.datacompliance.service.SensitiveDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensitiveDataServiceImpl implements SensitiveDataService {

    @Autowired
    SensitiveDataMapper sensitiveDataMapper;
    @Override
    public void addSensitiveData(Integer data_source_id, String database_name, String table_name, String field_name, Integer rule_id,String rule_rank) {
        sensitiveDataMapper.addSensitiveData(data_source_id,database_name,table_name,field_name,rule_id,rule_rank);
    }


    @Override
    public void deleteSensitiveDataById(Integer id) {
        sensitiveDataMapper.deleteSensitiveDataById(id);
    }

    @Override
    public void updateSensitiveDataById(Integer id, String databaseName, String tableName, String fieldName, Integer ruleId, String ruleRank) {
        sensitiveDataMapper.updateSensitiveDataById(id, databaseName, tableName, fieldName, ruleId, ruleRank);
    }

    @Override
    public SensitiveData findSensitiveDataById(Integer id) {
        return sensitiveDataMapper.findSensitiveDataById(id);
    }

    @Override
    public List<SensitiveData> findSensitiveDataByDataSourceId(Integer dataSourceId) {
        return sensitiveDataMapper.findSensitiveDataByDataSourceId(dataSourceId);
    }

    @Override
    public List<SensitiveData> findDistinctDataAndFields() {
        return sensitiveDataMapper.findDistinctDataAndFields();
    }
}
