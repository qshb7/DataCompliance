package com.example.datacompliance.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LoginFailureLog {
    private Integer id;
    private LocalDateTime timestamp;
    private String accountUser;
    private String accountHost;
    private String loginIp;
    private Integer datasourceId;

}
