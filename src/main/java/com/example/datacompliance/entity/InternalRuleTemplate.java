package com.example.datacompliance.entity;

import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
public class InternalRuleTemplate {
    @NonNull
    private Integer id;

    private String name;
    private  String description;
    private  String source;
    private LocalDateTime createTime;
    private LocalDateTime editTime;
}
