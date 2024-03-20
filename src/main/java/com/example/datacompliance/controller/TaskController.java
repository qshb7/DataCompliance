package com.example.datacompliance.controller;
import com.example.datacompliance.entity.Result;
import com.example.datacompliance.entity.Task;
import com.example.datacompliance.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/task")
@Validated
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/add")
    public Result addTask(@RequestBody Task task) {
        taskService.addTask(task);
        return Result.success();
    }

    @PutMapping("/update")
    public Result updateTask(@RequestBody Task task) {
        taskService.updateTask(task);
        return Result.success();
    }

    @DeleteMapping("/delete")
    public Result deleteTask(@RequestParam Integer id) {
        taskService.deleteTask(id);
        return Result.success();
    }

    @GetMapping("/get")
    public Result getTaskById(@RequestParam Integer id) {
        Task task = taskService.getTaskById(id);
        return Result.success(task);
    }

    @GetMapping("/all")
    public Result getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return Result.success(tasks);
    }

    @GetMapping("/last-id")
    public Result getLastInsertedTaskId() {
        Integer lastInsertedId = taskService.getLastInsertedTaskId();
        return Result.success(lastInsertedId);
    }
}