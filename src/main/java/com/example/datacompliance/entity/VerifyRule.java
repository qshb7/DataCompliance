package com.example.datacompliance.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VerifyRule {
    //内容规则
    private String contentRule;
    //字段规则
    private String metaRule;
    private String matchOperators;
    private String verifyContent;
    private String verifyMeta;
}
