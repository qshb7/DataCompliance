package com.example.datacompliance.mapper;

import com.example.datacompliance.entity.Rule;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface RuleMapper {
    @Insert("insert into rule (name, description, match_operators, source,status, content_rule, meta_rule) VALUES "+
            "(#{name},#{description},#{match_operators},#{source},#{status},#{content_rule},#{meta_rule})")
    void addRule(String name,String description,String match_operators,String source,Integer status,String content_rule,String meta_rule);

    @Delete("delete from rule where id=#{id}")
    void  deleteRule(Integer id);

    @Update("update rule set name=#{name},description=#{description},match_operators=#{match_operators},"+
            "status=#{status},content_rule=#{content_rule},meta_rule=#{meta_rule} where id=#{id}")
    void  updateRule(Integer id,String name,String description,String match_operators,Integer status,String content_rule,String meta_rule);

    @Select("select * from rule")
    List<Rule> findRuleAll();

    @Select("select * from rule where name=#{name}")
    Rule findRuleByName(String name);

}
