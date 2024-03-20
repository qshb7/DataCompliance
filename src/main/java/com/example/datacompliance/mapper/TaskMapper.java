package com.example.datacompliance.mapper;

import com.example.datacompliance.entity.Task;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface TaskMapper {

    @Insert("INSERT INTO classify_task (name, description, data_source_id, template_id, execute_plan, cycle, start_time, last_finished_time, status) " +
            "VALUES (#{name}, #{description}, #{dataSourceId}, #{templateId}, #{executePlan}, #{cycle}, #{startTime}, #{lastFinishedTime}, #{status})")
    void addTask(Task task);

    @Update("UPDATE classify_task SET name=#{name}, description=#{description}, data_source_id=#{dataSourceId}, template_id=#{templateId}, " +
            "execute_plan=#{executePlan}, cycle=#{cycle}, start_time=#{startTime}, last_finished_time=#{lastFinishedTime}, status=#{status} " +
            "WHERE id=#{id}")
    void updateTask(Task task);

    @Delete("DELETE FROM classify_task WHERE id=#{id}")
    void deleteTask(Integer id);

    @Select("SELECT * FROM classify_task WHERE id=#{id}")
    Task getTaskById(Integer id);

    @Select("SELECT * FROM classify_task")
    List<Task> getTasks();

    @Select("SELECT id FROM classify_task ORDER BY id DESC LIMIT 1")
    Integer getLastInsertedTaskId();


}

