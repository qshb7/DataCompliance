package com.example.datacompliance.controller;
import com.example.datacompliance.entity.Result;
import com.example.datacompliance.entity.SensitiveData;
import com.example.datacompliance.service.SensitiveDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/sensitiveData")
@Validated
public class SensitiveDataController {
    @Autowired
    private SensitiveDataService sensitiveDataService;

    @PostMapping("/add")
    public Result addSensitiveData(@RequestParam Integer dataSourceId,
                                   @RequestParam String databaseName,
                                   @RequestParam String tableName,
                                   @RequestParam String fieldName,
                                   @RequestParam Integer ruleId,
                                   @RequestParam String ruleRank) {
        sensitiveDataService.addSensitiveData(dataSourceId, databaseName, tableName, fieldName, ruleId, ruleRank);
        return Result.success();
    }

    @DeleteMapping("/delete")
    public Result deleteSensitiveData(@RequestParam Integer id) {
        sensitiveDataService.deleteSensitiveDataById(id);
        return Result.success();
    }

    @PutMapping("/update")
    public Result updateSensitiveData(@RequestParam Integer id,
                                      @RequestParam String databaseName,
                                      @RequestParam String tableName,
                                      @RequestParam String fieldName,
                                      @RequestParam Integer ruleId,
                                      @RequestParam String ruleRank) {
        sensitiveDataService.updateSensitiveDataById(id, databaseName, tableName, fieldName, ruleId, ruleRank);
        return Result.success();
    }

    @GetMapping("/get")
    public Result findSensitiveDataById(@RequestParam Integer id) {
        SensitiveData sensitiveData = sensitiveDataService.findSensitiveDataById(id);
        return Result.success(sensitiveData);
    }

    @GetMapping("/by-datasource")
    public Result findSensitiveDataByDataSourceId(@RequestParam Integer dataSourceId) {
        List<SensitiveData> sensitiveDataList = sensitiveDataService.findSensitiveDataByDataSourceId(dataSourceId);
        return Result.success(sensitiveDataList);
    }

    @GetMapping("/distinct")
    public Result findDistinctDataAndFields() {
        List<SensitiveData> sensitiveDataList = sensitiveDataService.findDistinctDataAndFields();
        return Result.success(sensitiveDataList);
    }
}

