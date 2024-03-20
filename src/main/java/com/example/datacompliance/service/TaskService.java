package com.example.datacompliance.service;
import com.example.datacompliance.entity.Task;

import java.util.List;

public interface TaskService {
    void addTask(Task task);
    void updateTask(Task task);
    void deleteTask(Integer id);
    Task getTaskById(Integer id);
    List<Task> getAllTasks();
    Integer getLastInsertedTaskId();

}
