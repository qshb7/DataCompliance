package com.example.datacompliance.mapper;

import com.example.datacompliance.entity.TemplateRule;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface TemplateRuleMapper {

    @Insert("INSERT INTO template_and_rules (template_id, classify_1, classify_2, classify_3, classify_4, rule_name, rule_rank) " +
            "VALUES (#{templateId}, #{classify1}, #{classify2}, #{classify3}, #{classify4}, #{ruleName}, #{ruleRank})")
    void addTemplateRule(TemplateRule TemplateRule);

    @Update("UPDATE template_and_rules SET template_id=#{templateId}, classify_1=#{classify1}, classify_2=#{classify2}, " +
            "classify_3=#{classify3}, classify_4=#{classify4}, rule_name=#{ruleName}, rule_rank=#{ruleRank} WHERE id=#{id}")
    void updateTemplateRule(TemplateRule TemplateRule);

    @Delete("DELETE FROM template_and_rules WHERE id=#{id}")
    void deleteTemplateRule(Integer id);

    @Select("SELECT * FROM template_and_rules WHERE id=#{id}")
    TemplateRule getTemplateRuleById(Integer id);

    @Select("SELECT * FROM template_and_rules")
    List<TemplateRule> getAllTemplateRules();

    @Select("select DISTINCT rule_name,rule_rank from template_and_rules where template_id=#{templateId} AND rule_name IS NOT NULL AND rule_name != ''")
    List<Map<String, Object>> getRulesByTemplateId(Integer templateId);
}
