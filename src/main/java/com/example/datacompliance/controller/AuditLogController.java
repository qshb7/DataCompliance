package com.example.datacompliance.controller;

import com.example.datacompliance.entity.Result;
import com.example.datacompliance.service.AuditAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/log")
@Validated
public class AuditLogController {
    @Autowired
    private AuditAnalysisService auditAnalysisService;

    @PostMapping("/analyze")
    public Result analyzeAuditLogs() {
        auditAnalysisService.analyzeAuditLogsAndSaveRisk();
        return Result.success("Analysis completed successfully.");
    }
}
