package com.example.datacompliance.service;

import com.example.datacompliance.entity.CustomRule;
import com.example.datacompliance.entity.VerifyRule;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CustomRuleService {
    void addCustomRule(CustomRule customRule);

    int verifyCustomRule(VerifyRule verifyRule);

    void deleteCustomRule(Integer id);

    void updateCustomRule(CustomRule customRule);

    List<CustomRule> findCustomRuleAll();
}
