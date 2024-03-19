package com.example.datacompliance.service;

public interface SensitiveDataService {
    void addSensitiveData(Integer data_source_id,String database_name, String table_name, String field_name, Integer rule_id,String rule_rank);
}
