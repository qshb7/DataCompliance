package com.example.datacompliance.controller;

import com.example.datacompliance.entity.Rule;
import com.example.datacompliance.entity.Result;
import com.example.datacompliance.entity.TaskParams;
import com.example.datacompliance.entity.VerifyRule;
import com.example.datacompliance.service.RuleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/rule")
@Validated
public class RuleController {
    private final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10); // 创建一个包含10个线程的定时任务执行器
    @Autowired
    private RuleService ruleService;

    @PostMapping("/addRule")
    public Result addRule(@RequestBody Rule rule){
        ruleService.addRule(rule);
        return Result.success();
    };

    @GetMapping("/verifyRule")
    public Result verifyRule(@RequestBody VerifyRule verifyRule) throws JsonProcessingException {
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

    @PostMapping("/newScanTask")
    public Result newScanTask(@RequestBody TaskParams taskParams){
        if ("立即".equals(taskParams.getExecutePlan())) {
            scheduledExecutorService.submit(() -> ruleService.newScanTask(taskParams)); // 提交任务给线程池执行
        } else if ("每天".equals(taskParams.getExecutePlan())) {
            LocalDateTime nextRunTime = taskParams.getStartTime().truncatedTo(ChronoUnit.DAYS).with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY));
            long initialDelay = Duration.between(LocalDateTime.now(), nextRunTime).toMillis();
            scheduledExecutorService.scheduleAtFixedRate(() -> {
                ruleService.newScanTask(taskParams);
            }, initialDelay, 1, TimeUnit.DAYS); // 每天执行一次
        } else if ("每周".equals(taskParams.getExecutePlan())) {
            LocalDateTime nextRunTime = taskParams.getStartTime().truncatedTo(ChronoUnit.DAYS).with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY));
            long initialDelay = Duration.between(LocalDateTime.now(), nextRunTime).toMillis();
            scheduledExecutorService.schedule(() -> {
                ruleService.newScanTask(taskParams);
            }, initialDelay, TimeUnit.MILLISECONDS);

            // 每周固定时间执行任务
            scheduledExecutorService.scheduleAtFixedRate(() -> {
                ruleService.newScanTask(taskParams);
            }, 7 * 24 * 60 * 60 * 1000, 7 * 24 * 60 * 60 * 1000, TimeUnit.MILLISECONDS); // 每周执行一次
        } else if ("每月".equals(taskParams.getExecutePlan())) {
            LocalDateTime nextRunTime = taskParams.getStartTime().truncatedTo(ChronoUnit.DAYS).with(TemporalAdjusters.firstDayOfNextMonth());
            long initialDelay = Duration.between(LocalDateTime.now(), nextRunTime).toMillis();
            scheduledExecutorService.schedule(() -> {
                ruleService.newScanTask(taskParams);
            }, initialDelay, TimeUnit.MILLISECONDS);

            // 每月固定时间执行任务
            scheduledExecutorService.scheduleAtFixedRate(() -> {
                ruleService.newScanTask(taskParams);
            }, 30 * 24 * 60 * 60 * 1000, 30 * 24 * 60 * 60 * 1000, TimeUnit.MILLISECONDS); // 每月执行一次
        }
        return Result.success();
    }

    @GetMapping("/findTemplateRules")
    public Result findTemplateRules(@RequestParam Integer templateId){
        return Result.success(ruleService.findTemplateRules(templateId));
    }

}
