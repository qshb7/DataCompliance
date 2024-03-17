package com.example.datacompliance.service.impl;

import com.example.datacompliance.entity.InternalRule;
import com.example.datacompliance.entity.InternalRuleTemplate;
import com.example.datacompliance.mapper.InternalRuleMapper;
import com.example.datacompliance.service.InternalRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InternalRuleServiceImpl implements InternalRuleService {
    @Autowired
    private InternalRuleMapper ruleMapper;
    @Override
    public List<InternalRuleTemplate> findTemplateAll() {

        return ruleMapper.findTemplateAll();
    }

    @Override
    public InternalRuleTemplate findTemplateById(Integer id) {
        return ruleMapper.findTemplateById(id);
    }

    @Override
    public InternalRuleTemplate findTemplateByName(String name) {
        return ruleMapper.findTemplateByName(name);
    }

    @Override
    public void addRuleTemplate(String name, String description) {
        String source="自定义";
        ruleMapper.addRuleTemplate(name,description,source);
    }

    @Override
    public void deleteRuleTemplate(Integer id) {
        ruleMapper.deleteRuleTemplate(id);
    }

    @Override
    public void updateRuleTemplate(Integer id, String name, String description) {
        ruleMapper.updateRuleTemplate(id,name,description);
    }

    @Override
    public List<InternalRule> findRulesByTemplateId(Integer templateId) {
        return null;
    }

    @Override
    public List<InternalRule> findAllRules() {
        return null;
    }


}
