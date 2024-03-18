package com.example.datacompliance.service;

import com.example.datacompliance.entity.Rule;
import com.example.datacompliance.entity.VerifyRule;

import java.util.List;

public interface RuleService {
    void addRule(Rule rule);

    int verifyRule(VerifyRule verifyRule);

    void deleteRule(Integer id);

    void updateRule(Rule rule);

    List<Rule> findRuleAll();
}
