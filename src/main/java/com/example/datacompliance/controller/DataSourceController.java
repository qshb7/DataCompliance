package com.example.datacompliance.controller;

import com.example.datacompliance.entity.DataSourceConfig;
import com.example.datacompliance.entity.Result;
import com.example.datacompliance.service.DataSourceService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("/dataSource")
@Validated
public class DataSourceController {
    @Autowired
    DataSourceService dataSourceService;
    @PostMapping("/newConnect")
    public Result newConnect(@RequestBody DataSourceConfig dataSourceConfig) throws SQLException, ClassNotFoundException, JsonProcessingException {
        dataSourceService.newConnect(dataSourceConfig);
        return Result.success();
    }
}
