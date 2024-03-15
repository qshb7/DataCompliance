package com.example.datacompliance.service.impl;

import com.example.datacompliance.entity.RuleTemplate;
import com.example.datacompliance.mapper.RuleMapper;
import com.example.datacompliance.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RuleServiceImpl implements RuleService {
    @Autowired
    private RuleMapper ruleMapper;
    @Override
    public List<RuleTemplate> findTemplateAll() {

        return ruleMapper.findTemplateAll();
    }

    @Override
    public RuleTemplate findTemplateById(Integer id) {
        return ruleMapper.findTemplateById(id);
    }

    @Override
    public RuleTemplate findTemplateByName(String name) {
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


}
