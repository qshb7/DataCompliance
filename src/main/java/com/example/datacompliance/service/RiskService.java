package com.example.datacompliance.service;

import com.example.datacompliance.entity.Risk;

import java.util.List;

public interface RiskService {
    // 存储单个风险信息
    void saveRisk(Risk risk);

    // 存储多个风险信息
    void saveRisks(List<Risk> risks);
}
