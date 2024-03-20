package com.example.datacompliance.entity;

import lombok.Data;
import lombok.NonNull;

@Data
public class Classification {
    @NonNull
    private Integer id;
    private String name;
    private String source;
}
