package com.example.datacompliance.service.impl;

import com.example.datacompliance.entity.Task;
import com.example.datacompliance.mapper.TaskMapper;
import com.example.datacompliance.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskMapper taskMapper;

    @Override
    public void addTask(Task task) {
        taskMapper.addTask(task);
    }

    @Override
    public void updateTask(Task task) {
        taskMapper.updateTask(task);
    }

    @Override
    public void deleteTask(Integer id) {
        taskMapper.deleteTask(id);
    }

    @Override
    public Task getTaskById(Integer id) {
        return taskMapper.getTaskById(id);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskMapper.getTasks();
    }

    @Override
    public Integer getLastInsertedTaskId() {
        return taskMapper.getLastInsertedTaskId();
    }
}
