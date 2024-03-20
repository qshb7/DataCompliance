package com.example.datacompliance.controller;

import com.example.datacompliance.entity.Result;
import com.example.datacompliance.entity.TemplateRule;
import com.example.datacompliance.service.TemplateRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/templateRule")
public class TemplateRuleController {

    @Autowired
    private TemplateRuleService TemplateRuleService;

    @PostMapping("/add")
    public Result addTemplateRule(@RequestBody TemplateRule TemplateRule) {
        TemplateRuleService.addTemplateRule(TemplateRule);
        return Result.success();
    }

    @PutMapping("/update")
    public Result updateTemplateRule(@RequestBody TemplateRule TemplateRule) {
        TemplateRuleService.updateTemplateRule(TemplateRule);
        return Result.success();
    }

    @DeleteMapping("/delete")
    public Result deleteTemplateRule(@RequestParam Integer id) {
        TemplateRuleService.deleteTemplateRule(id);
        return Result.success();
    }

    @GetMapping("/get")
    public Result getTemplateRuleById(@RequestParam Integer id) {
        TemplateRule TemplateRule = TemplateRuleService.getTemplateRuleById(id);
        return Result.success(TemplateRule);
    }

    @GetMapping("/all")
    public Result getAllTemplateRules() {
        List<TemplateRule> TemplateRules = TemplateRuleService.getAllTemplateRules();
        return Result.success(TemplateRules);
    }
}

