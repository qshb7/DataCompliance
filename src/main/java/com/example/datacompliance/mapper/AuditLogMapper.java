package com.example.datacompliance.mapper;

import com.example.datacompliance.entity.AuditLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AuditLogMapper {
    @Select("""
            SELECT * FROM connection_access
            UNION ALL
            SELECT * FROM general_access
            UNION ALL
            SELECT * FROM table_access;
            """)
    List<AuditLog> selectAllAuditLogs();
}
