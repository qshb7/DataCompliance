package com.example.datacompliance.service.impl;

import com.example.datacompliance.entity.Risk;
import com.example.datacompliance.mapper.RiskMapper;
import com.example.datacompliance.service.RiskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RiskServiceImpl implements RiskService {
    @Autowired
    private RiskMapper riskMapper;

    @Override
    public void saveRisk(Risk risk) {
        riskMapper.insertRisk(risk);
    }

    @Override
    public void saveRisks(List<Risk> risks) {
        riskMapper.insertRisks(risks);
    }
}
