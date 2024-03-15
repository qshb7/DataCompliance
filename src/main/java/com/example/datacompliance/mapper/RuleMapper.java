package com.example.datacompliance.mapper;

import com.example.datacompliance.entity.RuleTemplate;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RuleMapper {
    @Select("select * from rule_template")
    List<RuleTemplate> findTemplateAll();
    @Insert("insert into rule_template( name, description, source, create_time, edit_time)"+
    "values(#{name},#{description},#{source},now(),now())")
    void addRuleTemplate(String name, String description,String source);

    @Select("select * from rule_template where id=#{id}")
    RuleTemplate findTemplateById(Integer id);
    @Select("select * from rule_template where name=#{name}")
    RuleTemplate findTemplateByName(String name);

    @Delete("delete from rule_template where id=#{id}")
    void deleteRuleTemplate(Integer id);

    @Update("update rule_template set name=#{name},description=#{description},edit_time=now() where id=#{id}")
    void updateRuleTemplate(Integer id,String name,String description);

}
