package com.example.datacompliance.service;

import com.example.datacompliance.entity.TemplateRule;

import java.util.List;
import java.util.Map;

public interface TemplateRuleService {
    void addTemplateRule(TemplateRule TemplateRule);

    void updateTemplateRule(TemplateRule TemplateRule);

    void deleteTemplateRule(Integer id);

    TemplateRule getTemplateRuleById(Integer id);

    List<TemplateRule> getAllTemplateRules();

    List<Map<String, Object>> getRulesByTemplateId(Integer templateId);
}
