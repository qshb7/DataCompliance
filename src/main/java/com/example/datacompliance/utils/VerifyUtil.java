package com.example.datacompliance.utils;

import com.example.datacompliance.entity.RuleDetail;
import com.example.datacompliance.entity.RuleDetail.RuleContent.ExtendParameter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VerifyUtil {
    public static int verifyRuleContent(RuleDetail ruleContent, String verifyValue) {
        String operator = ruleContent.getOperator();
        if ("or".equals(operator)) {
            // 遍历规则列表，只要有一个规则匹配即可
            for (RuleDetail.RuleContent rule : ruleContent.getRuleContents()) {
                if (matchRule(rule, verifyValue)) {
                    return 1; // 匹配成功，返回 1 表示验证通过
                }
            }
            return 0; // 所有规则都不匹配，返回零值表示验证失败
        } else if ("and".equals(operator)) {
            // 遍历规则列表，所有规则都必须匹配才算通过
            for (RuleDetail.RuleContent rule : ruleContent.getRuleContents()) {
                if (!matchRule(rule, verifyValue)) {
                    return 0; // 有任何一个规则不匹配，返回零值表示验证失败
                }
            }
            return 1; // 所有规则都匹配，返回 1 表示验证通过
        } else {
            // 未知的运算符，返回非零值表示验证失败
            return 0;
        }
    }

    public static boolean matchRule(RuleDetail.RuleContent rule, String verifyValue) {
        String ruleType = rule.getRuleType();
        String ruleContent = rule.getRuleContent();
        boolean isFullWordMatch = false;
        boolean isIgnoreCase = false;
        if (rule.getExtendParameters() != null) {
            for (ExtendParameter e : rule.getExtendParameters()) {
                if (e.getName().equals("IsFullWordMatch")) {
                    isFullWordMatch = e.getValue().equals("true");
                }
                if (e.getName().equals("IsIgnoreCase")) {
                    isIgnoreCase = e.getValue().equals("true");
                }
            }

        }

        if ("regex".equals(ruleType)) {
            if (ruleContent == null) {
                // 如果 ruleContent 为 null，直接返回匹配成功
                return false;
            }
            // 使用正则表达式进行匹配
            Pattern pattern = Pattern.compile(ruleContent);
            Matcher matcher = pattern.matcher(verifyValue);
            return matcher.matches();
        } else if ("keyword".equals(ruleType)) {
            // 使用关键字匹配
            if (isIgnoreCase) {
                if (isFullWordMatch) {
                    return verifyValue.equalsIgnoreCase(ruleContent);
                } else {
                    return verifyValue.toLowerCase().contains(ruleContent.toLowerCase());
                }
            } else {
                if (isFullWordMatch) {
                    return verifyValue.equals(ruleContent);
                } else {
                    return verifyValue.contains(ruleContent);
                }
            }
        } else {
            // 不支持的规则类型，默认返回 false
            return false;
        }
    }

}
