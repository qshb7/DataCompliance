package com.example.datacompliance.service.impl;

import com.example.datacompliance.entity.Rule;
import com.example.datacompliance.entity.RuleDetail;
import com.example.datacompliance.entity.VerifyRule;
import com.example.datacompliance.mapper.RuleMapper;
import com.example.datacompliance.service.RuleService;
import com.example.datacompliance.utils.JsonUtil;
import com.example.datacompliance.utils.VerifyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RuleServiceImpl implements RuleService {
    @Autowired
    RuleMapper ruleMapper;
    @Override
    public void addRule(Rule rule) {
        ruleMapper.addRule(rule.getName(), rule.getDescription(), rule.getMatchOperators(), rule.getSource(), rule.getStatus(), rule.getContentRule(), rule.getMetaRule());
        System.out.println(rule);
    }

    @Override
    public int verifyRule(VerifyRule verifyRule) {
        RuleDetail contentRule = verifyRule.getContentRule();
        RuleDetail metaRule = verifyRule.getMetaRule();
        String matchOperators = verifyRule.getMatchOperators();
        String verifyContent = verifyRule.getVerifyContent();
        String verifyMeta = verifyRule.getVerifyMeta();

        int contentResult = VerifyUtil.verifyRuleContent(contentRule, verifyContent);
        int metaResult = VerifyUtil.verifyRuleContent(metaRule, verifyMeta);
        if(matchOperators.equals("or")){
            if(contentResult==1||metaResult==1){
                return 1;
            }
        }
        else {
            if(contentResult==1&&metaResult==1){
                return 1;
            }
        }
        return 0;
    }

    @Override
    public void deleteRule(Integer id) {
        ruleMapper.deleteRule(id);
    }

    @Override
    public void updateRule(Rule rule) {
        Integer id= rule.getId();
        String name= rule.getName();
        String description= rule.getDescription();
        String matchOperators= rule.getMatchOperators();
        Integer status= rule.getStatus();
        String contentRule= rule.getContentRule();
        String metaRule = rule.getMetaRule();
        ruleMapper.updateRule(id,name,description,matchOperators,status,contentRule,metaRule);
    }

    @Override
    public List<Rule> findRuleAll() {
        System.out.println(ruleMapper.findRuleAll());
        return ruleMapper.findRuleAll();
    }

    @Override
    public Rule findRuleByName(String name) {
        return ruleMapper.findRuleByName(name);
    }
}
