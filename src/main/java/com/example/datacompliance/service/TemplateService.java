package com.example.datacompliance.service;

import com.example.datacompliance.entity.TemplateRule;
import com.example.datacompliance.entity.Template;

import java.util.List;

public interface TemplateService {
    //规则模版接口
    List<Template> findTemplateAll();

    Template findTemplateById(Integer id);

    Template findTemplateByName(String name);
    void addRuleTemplate(String name,String description);

    void deleteRuleTemplate(Integer id);

    void updateRuleTemplate(Integer id,String name,String description);

    //具体规则接口
    List<TemplateRule> findRulesByTemplateId(Integer templateId);

    List<TemplateRule> findAllRules();


}
