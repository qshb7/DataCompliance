package com.example.datacompliance.controller;

import com.example.datacompliance.entity.CustomRule;
import com.example.datacompliance.entity.Result;
import com.example.datacompliance.entity.VerifyRule;
import com.example.datacompliance.service.CustomRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customRule")
@Validated
public class CustomRuleController {
    @Autowired
    private CustomRuleService customRuleService;

    @PostMapping("/addCustomRule")
    public Result addCustomRule(@RequestBody  CustomRule customRule){
        customRuleService.addCustomRule(customRule);
        return Result.success();
    };

    @GetMapping("/verifyCustomRule")
    public Result verifyCustomRule(@RequestBody VerifyRule verifyRule){
        return Result.success(customRuleService.verifyCustomRule(verifyRule));
    }

    @PostMapping("/deleteCustomRule")
    public Result deleteCustomRule(@RequestParam Integer id){
        customRuleService.deleteCustomRule(id);
        return Result.success();
    }

    @PostMapping("/updateCustomRule")
    public Result updateCustomRule(@RequestBody CustomRule customRule){
        customRuleService.updateCustomRule(customRule);
        return Result.success();
    }

    @GetMapping("/findCustomRuleAll")
    public Result findCustomRuleAll(){
        return Result.success(customRuleService.findCustomRuleAll());
    }

}
