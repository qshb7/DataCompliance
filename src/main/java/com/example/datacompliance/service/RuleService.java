package com.example.datacompliance.service;

import com.example.datacompliance.entity.RuleTemplate;

import java.util.List;

public interface RuleService {
    List<RuleTemplate> findTemplateAll();

    RuleTemplate findTemplateById(Integer id);

    RuleTemplate findTemplateByName(String name);
    void addRuleTemplate(String name,String description);

    void deleteRuleTemplate(Integer id);

    void updateRuleTemplate(Integer id,String name,String description);




}
