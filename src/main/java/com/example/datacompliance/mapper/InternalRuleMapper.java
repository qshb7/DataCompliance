package com.example.datacompliance.mapper;

import com.example.datacompliance.entity.InternalRuleTemplate;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface InternalRuleMapper {
    @Select("select * from internal_rule_template")
    List<InternalRuleTemplate> findTemplateAll();
    @Insert("insert into internal_rule_template( name, description, source, create_time, edit_time)"+
    "values(#{name},#{description},#{source},now(),now())")
    void addRuleTemplate(String name, String description,String source);

    @Select("select * from internal_rule_template where id=#{id}")
    InternalRuleTemplate findTemplateById(Integer id);
    @Select("select * from internal_rule_template where name=#{name}")
    InternalRuleTemplate findTemplateByName(String name);

    @Delete("delete from internal_rule_template where id=#{id}")
    void deleteRuleTemplate(Integer id);

    @Update("update internal_rule_template set name=#{name},description=#{description},edit_time=now() where id=#{id}")
    void updateRuleTemplate(Integer id,String name,String description);

}
