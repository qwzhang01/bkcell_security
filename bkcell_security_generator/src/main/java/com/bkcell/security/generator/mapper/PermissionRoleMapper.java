package com.bkcell.security.generator.mapper;

import com.bkcell.security.generator.pojo.PermissionRole;
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

public interface PermissionRoleMapper {
    @Delete({
        "delete from global_permission_role_tb",
        "where RpId = #{rpid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer rpid);

    @Insert({
        "insert into global_permission_role_tb (RoleId, PermissionId)",
        "values (#{roleid,jdbcType=INTEGER}, #{permissionid,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="rpid", before=false, resultType=Integer.class)
    int insert(PermissionRole record);

    @InsertProvider(type=PermissionRoleSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="rpid", before=false, resultType=Integer.class)
    int insertSelective(PermissionRole record);

    @Select({
        "select",
        "RpId, RoleId, PermissionId",
        "from global_permission_role_tb",
        "where RpId = #{rpid,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="RpId", property="rpid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="RoleId", property="roleid", jdbcType=JdbcType.INTEGER),
        @Result(column="PermissionId", property="permissionid", jdbcType=JdbcType.INTEGER)
    })
    PermissionRole selectByPrimaryKey(Integer rpid);

    @UpdateProvider(type=PermissionRoleSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(PermissionRole record);

    @Update({
        "update global_permission_role_tb",
        "set RoleId = #{roleid,jdbcType=INTEGER},",
          "PermissionId = #{permissionid,jdbcType=INTEGER}",
        "where RpId = #{rpid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(PermissionRole record);
}