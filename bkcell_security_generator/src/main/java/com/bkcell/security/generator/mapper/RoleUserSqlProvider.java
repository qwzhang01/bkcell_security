package com.bkcell.security.generator.mapper;

import com.bkcell.security.generator.pojo.RoleUser;
import org.apache.ibatis.jdbc.SQL;

public class RoleUserSqlProvider {

    public String insertSelective(RoleUser record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("rbac_role_user_tb");
        
        if (record.getRoleid() != null) {
            sql.VALUES("RoleId", "#{roleid,jdbcType=INTEGER}");
        }
        
        if (record.getUserid() != null) {
            sql.VALUES("UserId", "#{userid,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }
}