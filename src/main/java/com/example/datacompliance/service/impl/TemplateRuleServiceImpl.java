package com.example.datacompliance.service.impl;

import com.example.datacompliance.entity.TemplateRule;
import com.example.datacompliance.mapper.TemplateRuleMapper;
import com.example.datacompliance.service.TemplateRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TemplateRuleServiceImpl implements TemplateRuleService {
    @Autowired
    private TemplateRuleMapper TemplateRuleMapper;

    @Override
    public void addTemplateRule(TemplateRule TemplateRule) {
        TemplateRuleMapper.addTemplateRule(TemplateRule);
    }

    @Override
    public void updateTemplateRule(TemplateRule TemplateRule) {
        TemplateRuleMapper.updateTemplateRule(TemplateRule);
    }

    @Override
    public void deleteTemplateRule(Integer id) {
        TemplateRuleMapper.deleteTemplateRule(id);
    }

    @Override
    public TemplateRule getTemplateRuleById(Integer id) {
        return TemplateRuleMapper.getTemplateRuleById(id);
    }

    @Override
    public List<TemplateRule> getAllTemplateRules() {
        return TemplateRuleMapper.getAllTemplateRules();
    }

    @Override
    public List<Map<String, Object>> getRulesByTemplateId(Integer templateId) {
        return TemplateRuleMapper.getRulesByTemplateId(templateId);
    }
}
