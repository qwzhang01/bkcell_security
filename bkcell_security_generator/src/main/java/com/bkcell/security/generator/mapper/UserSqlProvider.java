package com.bkcell.security.generator.mapper;

import com.bkcell.security.generator.pojo.User;
import org.apache.ibatis.jdbc.SQL;

public class UserSqlProvider {

    public String insertSelective(User record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("global_user_tb");
        
        if (record.getUsername() != null) {
            sql.VALUES("UserName", "#{username,jdbcType=VARCHAR}");
        }
        
        if (record.getPassword() != null) {
            sql.VALUES("Password", "#{password,jdbcType=VARCHAR}");
        }
        
        if (record.getPasswordtype() != null) {
            sql.VALUES("PasswordType", "#{passwordtype,jdbcType=INTEGER}");
        }
        
        if (record.getRealname() != null) {
            sql.VALUES("RealName", "#{realname,jdbcType=VARCHAR}");
        }
        
        if (record.getPhonenum() != null) {
            sql.VALUES("PhoneNum", "#{phonenum,jdbcType=VARCHAR}");
        }
        
        if (record.getOrgid() != null) {
            sql.VALUES("OrgId", "#{orgid,jdbcType=INTEGER}");
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

    public String updateByPrimaryKeySelective(User record) {
        SQL sql = new SQL();
        sql.UPDATE("global_user_tb");
        
        if (record.getUsername() != null) {
            sql.SET("UserName = #{username,jdbcType=VARCHAR}");
        }
        
        if (record.getPassword() != null) {
            sql.SET("Password = #{password,jdbcType=VARCHAR}");
        }
        
        if (record.getPasswordtype() != null) {
            sql.SET("PasswordType = #{passwordtype,jdbcType=INTEGER}");
        }
        
        if (record.getRealname() != null) {
            sql.SET("RealName = #{realname,jdbcType=VARCHAR}");
        }
        
        if (record.getPhonenum() != null) {
            sql.SET("PhoneNum = #{phonenum,jdbcType=VARCHAR}");
        }
        
        if (record.getOrgid() != null) {
            sql.SET("OrgId = #{orgid,jdbcType=INTEGER}");
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
        
        sql.WHERE("UserId = #{userid,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}