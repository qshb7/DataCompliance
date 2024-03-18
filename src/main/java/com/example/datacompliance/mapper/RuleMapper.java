package com.example.datacompliance.mapper;

import com.example.datacompliance.entity.Rule;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RuleMapper {
    @Insert("insert into rules (name, description, matchOperators, source,status, contentRule, metaRule) VALUES "+
            "(#{name},#{description},#{matchOperators},#{source},#{status},#{contentRule},#{metaRule})")
    void addRule(String name,String description,String matchOperators,String source,Integer status,String contentRule,String metaRule);

    @Delete("delete from rules where id=#{id}")
    void  deleteRule(Integer id);

    @Update("update rules set name=#{name},description=#{description},matchOperators=#{matchOperators},"+
            "status=#{status},contentRule=#{contentRule},metaRule=#{metaRule} where id=#{id}")
    void  updateRule(Integer id,String name,String description,String matchOperators,Integer status,String contentRule,String metaRule);

    @Select("select * from rules")
    List<Rule> findRuleAll();
}
