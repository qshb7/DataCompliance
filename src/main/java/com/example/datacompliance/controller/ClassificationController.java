package com.example.datacompliance.controller;


import com.example.datacompliance.entity.Classification;
import com.example.datacompliance.entity.Result;
import com.example.datacompliance.service.ClassificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classification")
public class ClassificationController {

    @Autowired
    private ClassificationService classificationService;

    @PostMapping("/add")
    public Result addClassification(@RequestBody Classification classification) {
        classificationService.addClassification(classification);
        return Result.success();
    }

    @PutMapping("/update")
    public Result updateClassification(@RequestBody Classification classification) {
        classificationService.updateClassification(classification);
        return Result.success();
    }

    @DeleteMapping("/delete")
    public Result deleteClassification(@RequestParam Integer id) {
        classificationService.deleteClassification(id);
        return Result.success();
    }

    @GetMapping("/get")
    public Result getClassificationById(@RequestParam Integer id) {
        Classification classification = classificationService.getClassificationById(id);
        return Result.success(classification);
    }

    @GetMapping("/all")
    public Result getAllClassifications() {
        List<Classification> classifications = classificationService.getAllClassifications();
        return Result.success(classifications);
    }
}
