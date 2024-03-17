package com.example.datacompliance.mapper;

import com.example.datacompliance.entity.CustomRule;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface CustomRuleMapper {
    @Insert("insert into custom_rules (name, description, matchOperators, source,status, contentRule, metaRule) VALUES "+
            "(#{name},#{description},#{matchOperators},#{source},#{status},#{contentRule},#{metaRule})")
    void addCustomRule(String name,String description,String matchOperators,String source,Integer status,String contentRule,String metaRule);

    @Delete("delete from custom_rules where id=#{id}")
    void  deleteCustomRule(Integer id);

    @Update("update custom_rules set name=#{name},description=#{description},matchOperators=#{matchOperators},"+
            "status=#{status},contentRule=#{contentRule},metaRule=#{metaRule} where id=#{id}")
    void  updateCustomRule(Integer id,String name,String description,String matchOperators,Integer status,String contentRule,String metaRule);

    @Select("select * from custom_rules")
    List<CustomRule> findCustomRuleAll();
}
