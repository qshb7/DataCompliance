package com.example.datacompliance.service.impl;

import com.example.datacompliance.entity.CustomRule;
import com.example.datacompliance.entity.CustomRuleContent;
import com.example.datacompliance.entity.VerifyRule;
import com.example.datacompliance.mapper.CustomRuleMapper;
import com.example.datacompliance.service.CustomRuleService;
import com.example.datacompliance.utils.VerifyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomRuleServiceImpl implements CustomRuleService {
    @Autowired
    CustomRuleMapper customRuleMapper;
    @Override
    public void addCustomRule(CustomRule customRule) {
        customRuleMapper.addCustomRule(customRule.getName(),customRule.getDescription(),customRule.getMatchOperators(),customRule.getSource(),customRule.getStatus(),customRule.getContentRule().toJsonString(),customRule.getMetaRule().toJsonString());
        System.out.println(customRule);
    }

    @Override
    public int verifyCustomRule(VerifyRule verifyRule) {
        CustomRuleContent contentRule = verifyRule.getContentRule();
        CustomRuleContent metaRule = verifyRule.getMetaRule();
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
    public void deleteCustomRule(Integer id) {
        customRuleMapper.deleteCustomRule(id);
    }

    @Override
    public void updateCustomRule(CustomRule customRule) {
        Integer id=customRule.getId();
        String name=customRule.getName();
        String description= customRule.getDescription();
        String matchOperators=customRule.getMatchOperators();
        Integer status=customRule.getStatus();
        String contentRule=customRule.getContentRule().toJsonString();
        String metaRule =customRule.getMetaRule().toJsonString();
        customRuleMapper.updateCustomRule(id,name,description,matchOperators,status,contentRule,metaRule);
    }

    @Override
    public List<CustomRule> findCustomRuleAll() {
        return customRuleMapper.findCustomRuleAll();
    }
}
