package com.example.datacompliance.service.impl;

import com.example.datacompliance.mapper.SensitiveDataMapper;
import com.example.datacompliance.service.SensitiveDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SensitiveDataServiceImpl implements SensitiveDataService {

    @Autowired
    SensitiveDataMapper sensitiveDataMapper;
    @Override
    public void addSensitiveData(Integer data_source_id, String database_name, String table_name, String field_name, Integer rule_id,String rule_rank) {
        sensitiveDataMapper.addSensitiveData(data_source_id,database_name,table_name,field_name,rule_id,rule_rank);
    }
}
