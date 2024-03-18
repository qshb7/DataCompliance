package com.example.datacompliance.mapper;

import com.example.datacompliance.entity.Template;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TemplateMapper {
    @Select("select * from templates")
    List<Template> findTemplateAll();
    @Insert("insert into templates( name, description, source, create_time, edit_time)"+
    "values(#{name},#{description},#{source},now(),now())")
    void addRuleTemplate(String name, String description,String source);

    @Select("select * from templates where id=#{id}")
    Template findTemplateById(Integer id);
    @Select("select * from templates where name=#{name}")
    Template findTemplateByName(String name);

    @Delete("delete from templates where id=#{id}")
    void deleteRuleTemplate(Integer id);

    @Update("update templates set name=#{name},description=#{description},edit_time=now() where id=#{id}")
    void updateRuleTemplate(Integer id,String name,String description);

}
