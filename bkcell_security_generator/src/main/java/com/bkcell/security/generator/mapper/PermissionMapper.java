package com.bkcell.security.generator.mapper;

import com.bkcell.security.generator.pojo.Permission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface PermissionMapper {
    @Delete({
        "delete from global_permission_tb",
        "where PermissionId = #{permissionid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer permissionid);

    @Insert({
        "insert into global_permission_tb (Menu, SubMenu, ",
        "Operate, SortNo, ",
        "MenuIcon, MenuFlag, ",
        "MenuHost, MenuRoute)",
        "values (#{menu,jdbcType=VARCHAR}, #{submenu,jdbcType=VARCHAR}, ",
        "#{operate,jdbcType=VARCHAR}, #{sortno,jdbcType=VARCHAR}, ",
        "#{menuicon,jdbcType=VARCHAR}, #{menuflag,jdbcType=VARCHAR}, ",
        "#{menuhost,jdbcType=VARCHAR}, #{menuroute,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="permissionid", before=false, resultType=Integer.class)
    int insert(Permission record);

    @InsertProvider(type=PermissionSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="permissionid", before=false, resultType=Integer.class)
    int insertSelective(Permission record);

    @Select({
        "select",
        "PermissionId, Menu, SubMenu, Operate, SortNo, MenuIcon, MenuFlag, MenuHost, ",
        "MenuRoute",
        "from global_permission_tb",
        "where PermissionId = #{permissionid,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="PermissionId", property="permissionid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="Menu", property="menu", jdbcType=JdbcType.VARCHAR),
        @Result(column="SubMenu", property="submenu", jdbcType=JdbcType.VARCHAR),
        @Result(column="Operate", property="operate", jdbcType=JdbcType.VARCHAR),
        @Result(column="SortNo", property="sortno", jdbcType=JdbcType.VARCHAR),
        @Result(column="MenuIcon", property="menuicon", jdbcType=JdbcType.VARCHAR),
        @Result(column="MenuFlag", property="menuflag", jdbcType=JdbcType.VARCHAR),
        @Result(column="MenuHost", property="menuhost", jdbcType=JdbcType.VARCHAR),
        @Result(column="MenuRoute", property="menuroute", jdbcType=JdbcType.VARCHAR)
    })
    Permission selectByPrimaryKey(Integer permissionid);

    @UpdateProvider(type=PermissionSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Permission record);

    @Update({
        "update global_permission_tb",
        "set Menu = #{menu,jdbcType=VARCHAR},",
          "SubMenu = #{submenu,jdbcType=VARCHAR},",
          "Operate = #{operate,jdbcType=VARCHAR},",
          "SortNo = #{sortno,jdbcType=VARCHAR},",
          "MenuIcon = #{menuicon,jdbcType=VARCHAR},",
          "MenuFlag = #{menuflag,jdbcType=VARCHAR},",
          "MenuHost = #{menuhost,jdbcType=VARCHAR},",
          "MenuRoute = #{menuroute,jdbcType=VARCHAR}",
        "where PermissionId = #{permissionid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Permission record);
}