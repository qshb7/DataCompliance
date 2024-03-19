package com.example.datacompliance.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class Task {
    @NonNull
    private Integer id;
    private String name;
    private String description;
    private Integer dataSourceId;
    private Integer templateId;
    private String executePlan;
    private String cycle;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;
    private String status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastFinishedTime;

}
