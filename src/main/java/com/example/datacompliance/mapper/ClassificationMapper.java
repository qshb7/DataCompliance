package com.example.datacompliance.mapper;

import com.example.datacompliance.entity.Classification;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ClassificationMapper {
    @Insert("INSERT INTO classification (name, source) VALUES (#{name}, #{source})")
    void addClassification(Classification classification);

    @Update("UPDATE classification SET name=#{name}, source=#{source} WHERE id=#{id}")
    void updateClassification(Classification classification);

    @Delete("DELETE FROM classification WHERE id=#{id}")
    void deleteClassification(Integer id);

    @Select("SELECT * FROM classification WHERE id=#{id}")
    Classification getClassificationById(Integer id);

    @Select("SELECT * FROM classification")
    List<Classification> getAllClassifications();
}
