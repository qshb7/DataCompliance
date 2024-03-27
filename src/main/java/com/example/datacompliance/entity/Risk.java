package com.example.datacompliance.entity;

import lombok.Data;

@Data
public class Risk {
    private Integer id;
    private Integer datasourceId;
    private String type;
    private String description;
}
