package com.bkcell.security.generator.mapper;

import com.bkcell.security.generator.pojo.PermissionRole;
import org.apache.ibatis.jdbc.SQL;

public class PermissionRoleSqlProvider {

    public String insertSelective(PermissionRole record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("global_permission_role_tb");
        
        if (record.getRoleid() != null) {
            sql.VALUES("RoleId", "#{roleid,jdbcType=INTEGER}");
        }
        
        if (record.getPermissionid() != null) {
            sql.VALUES("PermissionId", "#{permissionid,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(PermissionRole record) {
        SQL sql = new SQL();
        sql.UPDATE("global_permission_role_tb");
        
        if (record.getRoleid() != null) {
            sql.SET("RoleId = #{roleid,jdbcType=INTEGER}");
        }
        
        if (record.getPermissionid() != null) {
            sql.SET("PermissionId = #{permissionid,jdbcType=INTEGER}");
        }
        
        sql.WHERE("RpId = #{rpid,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}