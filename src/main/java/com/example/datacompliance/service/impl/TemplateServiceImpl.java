package com.example.datacompliance.service.impl;

import com.example.datacompliance.entity.TemplateRule;
import com.example.datacompliance.entity.Template;
import com.example.datacompliance.mapper.TemplateMapper;
import com.example.datacompliance.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemplateServiceImpl implements TemplateService {
    @Autowired
    private TemplateMapper templateMapper;
    @Override
    public List<Template> findTemplateAll() {

        return templateMapper.findTemplateAll();
    }

    @Override
    public Template findTemplateById(Integer id) {
        return templateMapper.findTemplateById(id);
    }

    @Override
    public Template findTemplateByName(String name) {
        return templateMapper.findTemplateByName(name);
    }

    @Override
    public void addRuleTemplate(String name, String description) {
        String source="自定义";
        templateMapper.addRuleTemplate(name,description,source);
    }

    @Override
    public void deleteRuleTemplate(Integer id) {
        templateMapper.deleteRuleTemplate(id);
    }

    @Override
    public void updateRuleTemplate(Integer id, String name, String description) {
        templateMapper.updateRuleTemplate(id,name,description);
    }

    @Override
    public List<TemplateRule> findRulesByTemplateId(Integer templateId) {
        return null;
    }

    @Override
    public List<TemplateRule> findAllRules() {
        return null;
    }


}
