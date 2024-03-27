package com.example.datacompliance.mapper;

import com.example.datacompliance.entity.Risk;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RiskMapper {
    // 插入风险信息
    @Insert("INSERT INTO your_risk_table (datasourceId, type, description)" +
            "VALUES (#{datasourceId}, #{type}, #{description})")
    void insertRisk(Risk risk);

    // 批量插入风险信息
    @Insert("""
            INSERT INTO your_risk_table (datasourceId, type, description)
            VALUES
            <foreach collection="risks" item="risk" separator=",">
                (#{risk.datasourceId}, #{risk.type}, #{risk.description})
            </foreach>""")
    void insertRisks(List<Risk> risks);
}
