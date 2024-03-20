package com.example.datacompliance.service;

import com.example.datacompliance.entity.SensitiveData;

import java.util.List;

public interface SensitiveDataService {
    void addSensitiveData(Integer dataSourceId, String databaseName, String tableName, String fieldName, Integer ruleId, String ruleRank);

    void deleteSensitiveDataById(Integer id);

    void updateSensitiveDataById(Integer id, String databaseName, String tableName, String fieldName, Integer ruleId, String ruleRank);

    SensitiveData findSensitiveDataById(Integer id);

    List<SensitiveData> findSensitiveDataByDataSourceId(Integer dataSourceId);

    List<SensitiveData> findDistinctDataAndFields();
}
