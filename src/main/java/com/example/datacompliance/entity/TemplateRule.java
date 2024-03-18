package com.example.datacompliance.entity;

import lombok.Data;
import lombok.NonNull;

@Data
public class TemplateRule {
    @NonNull
    private Integer id;

    private Integer templateId;
    private String classify1;
    private String classify2;
    private String classify3;
    private String classify4;
    private String ruleName;
    private String ruleNameEng;
    private String ruleRank;


}
