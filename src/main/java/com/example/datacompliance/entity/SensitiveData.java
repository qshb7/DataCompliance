package com.example.datacompliance.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class SensitiveData {
    @NonNull
    private Integer id;
    private Integer dataSourceId;
    private String databaseName;
    private String tableName;
    private String fieldName;
    private Integer ruleId;
    private String ruleRank;
}
