package com.example.datacompliance.service;

import com.example.datacompliance.entity.InternalRule;
import com.example.datacompliance.entity.InternalRuleTemplate;

import java.util.List;

public interface InternalRuleService {
    //规则模版接口
    List<InternalRuleTemplate> findTemplateAll();

    InternalRuleTemplate findTemplateById(Integer id);

    InternalRuleTemplate findTemplateByName(String name);
    void addRuleTemplate(String name,String description);

    void deleteRuleTemplate(Integer id);

    void updateRuleTemplate(Integer id,String name,String description);

    //具体规则接口
    List<InternalRule> findRulesByTemplateId(Integer templateId);

    List<InternalRule> findAllRules();


}
