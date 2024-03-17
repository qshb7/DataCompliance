package com.example.datacompliance.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class CustomRule {
    @NonNull
    private Integer id;

    private String name;
    private String description;
    private String source;
    private String matchOperators;
    private Integer status;
    //内容规则
    private CustomRuleContent contentRule;
    //字段规则
    private CustomRuleContent metaRule;

}
