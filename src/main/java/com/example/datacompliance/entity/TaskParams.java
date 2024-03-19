package com.example.datacompliance.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class TaskParams {
    @NonNull
    private Integer id;
    private String name;
    private String description;
    private String dataSource;
    private Integer templateId;
    private String executePlan;
    private String cycle;
    private LocalDateTime startTime;

}
