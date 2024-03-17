package com.example.datacompliance.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.util.List;

@Data
public class CustomRuleContent {
    private String operator;
    private List<RuleContent> ruleContents;
    @Data
    public static  class RuleContent {
        private String ruleType;
        private String ruleContent;
        private List<ExtendParameter> extendParameters;

        @Data
        public static class ExtendParameter {
            private String name;
            private String value;
        }
    }

    public String toJsonString() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(this);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
