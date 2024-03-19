package com.example.datacompliance.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RuleDetail {
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
}
