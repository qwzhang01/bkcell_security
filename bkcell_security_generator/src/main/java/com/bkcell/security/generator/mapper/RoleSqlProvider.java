package com.bkcell.security.generator.mapper;

import com.bkcell.security.generator.pojo.Role;
import org.apache.ibatis.jdbc.SQL;

public class RoleSqlProvider {

    public String insertSelective(Role record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("rbac_role_tb");
        
        if (record.getRolename() != null) {
            sql.VALUES("RoleName", "#{rolename,jdbcType=VARCHAR}");
        }
        
        if (record.getDescription() != null) {
            sql.VALUES("Description", "#{description,jdbcType=VARCHAR}");
        }
        
        if (record.getCreatedby() != null) {
            sql.VALUES("CreatedBy", "#{createdby,jdbcType=VARCHAR}");
        }
        
        if (record.getCreatedon() != null) {
            sql.VALUES("CreatedOn", "#{createdon,jdbcType=TIMESTAMP}");
        }
        
        if (record.getModifiedby() != null) {
            sql.VALUES("ModifiedBy", "#{modifiedby,jdbcType=VARCHAR}");
        }
        
        if (record.getModifiedon() != null) {
            sql.VALUES("ModifiedOn", "#{modifiedon,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Role record) {
        SQL sql = new SQL();
        sql.UPDATE("rbac_role_tb");
        
        if (record.getRolename() != null) {
            sql.SET("RoleName = #{rolename,jdbcType=VARCHAR}");
        }
        
        if (record.getDescription() != null) {
            sql.SET("Description = #{description,jdbcType=VARCHAR}");
        }
        
        if (record.getCreatedby() != null) {
            sql.SET("CreatedBy = #{createdby,jdbcType=VARCHAR}");
        }
        
        if (record.getCreatedon() != null) {
            sql.SET("CreatedOn = #{createdon,jdbcType=TIMESTAMP}");
        }
        
        if (record.getModifiedby() != null) {
            sql.SET("ModifiedBy = #{modifiedby,jdbcType=VARCHAR}");
        }
        
        if (record.getModifiedon() != null) {
            sql.SET("ModifiedOn = #{modifiedon,jdbcType=TIMESTAMP}");
        }
        
        sql.WHERE("RoleId = #{roleid,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}