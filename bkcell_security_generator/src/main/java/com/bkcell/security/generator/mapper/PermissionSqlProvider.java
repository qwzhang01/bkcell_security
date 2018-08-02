package com.bkcell.security.generator.mapper;

import com.bkcell.security.generator.pojo.Permission;
import org.apache.ibatis.jdbc.SQL;

public class PermissionSqlProvider {

    public String insertSelective(Permission record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("global_permission_tb");
        
        if (record.getMenu() != null) {
            sql.VALUES("Menu", "#{menu,jdbcType=VARCHAR}");
        }
        
        if (record.getSubmenu() != null) {
            sql.VALUES("SubMenu", "#{submenu,jdbcType=VARCHAR}");
        }
        
        if (record.getOperate() != null) {
            sql.VALUES("Operate", "#{operate,jdbcType=VARCHAR}");
        }
        
        if (record.getSortno() != null) {
            sql.VALUES("SortNo", "#{sortno,jdbcType=VARCHAR}");
        }
        
        if (record.getMenuicon() != null) {
            sql.VALUES("MenuIcon", "#{menuicon,jdbcType=VARCHAR}");
        }
        
        if (record.getMenuflag() != null) {
            sql.VALUES("MenuFlag", "#{menuflag,jdbcType=VARCHAR}");
        }
        
        if (record.getMenuhost() != null) {
            sql.VALUES("MenuHost", "#{menuhost,jdbcType=VARCHAR}");
        }
        
        if (record.getMenuroute() != null) {
            sql.VALUES("MenuRoute", "#{menuroute,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Permission record) {
        SQL sql = new SQL();
        sql.UPDATE("global_permission_tb");
        
        if (record.getMenu() != null) {
            sql.SET("Menu = #{menu,jdbcType=VARCHAR}");
        }
        
        if (record.getSubmenu() != null) {
            sql.SET("SubMenu = #{submenu,jdbcType=VARCHAR}");
        }
        
        if (record.getOperate() != null) {
            sql.SET("Operate = #{operate,jdbcType=VARCHAR}");
        }
        
        if (record.getSortno() != null) {
            sql.SET("SortNo = #{sortno,jdbcType=VARCHAR}");
        }
        
        if (record.getMenuicon() != null) {
            sql.SET("MenuIcon = #{menuicon,jdbcType=VARCHAR}");
        }
        
        if (record.getMenuflag() != null) {
            sql.SET("MenuFlag = #{menuflag,jdbcType=VARCHAR}");
        }
        
        if (record.getMenuhost() != null) {
            sql.SET("MenuHost = #{menuhost,jdbcType=VARCHAR}");
        }
        
        if (record.getMenuroute() != null) {
            sql.SET("MenuRoute = #{menuroute,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("PermissionId = #{permissionid,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}