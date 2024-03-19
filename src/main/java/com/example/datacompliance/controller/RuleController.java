package com.example.datacompliance.controller;

import com.example.datacompliance.entity.Rule;
import com.example.datacompliance.entity.Result;
import com.example.datacompliance.entity.VerifyRule;
import com.example.datacompliance.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rule")
@Validated
public class RuleController {
    @Autowired
    private RuleService ruleService;

    @PostMapping("/addRule")
    public Result addRule(@RequestBody Rule rule){
        ruleService.addRule(rule);
        return Result.success();
    };

    @GetMapping("/verifyRule")
    public Result verifyRule(@RequestBody VerifyRule verifyRule){
        return Result.success(ruleService.verifyRule(verifyRule));
    }

    @PostMapping("/deleteRule")
    public Result deleteRule(@RequestParam Integer id){
        ruleService.deleteRule(id);
        return Result.success();
    }

    @PostMapping("/updateRule")
    public Result updateRule(@RequestBody Rule rule){
        ruleService.updateRule(rule);
        return Result.success();
    }

    @GetMapping("/findRuleAll")
    public Result findRuleAll(){
        return Result.success(ruleService.findRuleAll());
    }

    @GetMapping("/findRuleByName")
    public Result findRuleByName(@RequestParam String name){
        return Result.success(ruleService.findRuleByName(name));
    }


}
