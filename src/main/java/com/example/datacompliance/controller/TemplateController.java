package com.example.datacompliance.controller;


import com.example.datacompliance.entity.Result;
import com.example.datacompliance.entity.Template;
import com.example.datacompliance.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/template")
@Validated
public class TemplateController {
    @Autowired
    private TemplateService ruleService;

    @GetMapping("/allRuleTemplates")
    public Result getAllRuleTemplates() {
        return Result.success(ruleService.findTemplateAll());
    }

    @PostMapping("/addRuleTemplate")
    public Result addRuleTemplate(String name, String description) {
        Template r = ruleService.findTemplateByName(name);
        if (r == null) {
            ruleService.addRuleTemplate(name, description);
            return Result.success();
        } else {
            return Result.error("模版名称已被占用");
        }
    }

    @GetMapping("/findTemplateById")
    public Result findTemplateById(Integer id) {
        return Result.success(ruleService.findTemplateById(id));
    }

    @GetMapping("/findTemplateByName")
    public Result findTemplateByName(String name) {
        return Result.success(ruleService.findTemplateByName(name));
    }

    @PostMapping ("/deleteRuleTemplate")
    public Result deleteRuleTemplate(Integer id) {
        ruleService.deleteRuleTemplate((id));
        return Result.success();
    }

    @PostMapping("/updateRuleTemplate")
    public Result updateRuleTemplate(Integer id, String name, String description) {
        ruleService.updateRuleTemplate(id, name, description);
        return Result.success();
    }


}
