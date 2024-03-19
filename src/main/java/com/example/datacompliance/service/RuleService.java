package com.example.datacompliance.service;

import com.example.datacompliance.entity.Rule;
import com.example.datacompliance.entity.TaskParams;
import com.example.datacompliance.entity.VerifyRule;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;
import java.util.Map;

public interface RuleService {
    void addRule(Rule rule);

    int verifyRule(VerifyRule verifyRule) throws JsonProcessingException;

    void deleteRule(Integer id);

    void updateRule(Rule rule);

    List<Rule> findRuleAll();

    Rule findRuleByName(String name);

    void newScanTask(TaskParams taskParams);

    List<Map<String, Object>> findTemplateRules(Integer templateId);
}
