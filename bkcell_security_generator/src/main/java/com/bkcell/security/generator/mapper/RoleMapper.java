package com.bkcell.security.generator.mapper;

import com.bkcell.security.generator.pojo.Role;
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

public interface RoleMapper {
    @Delete({
        "delete from rbac_role_tb",
        "where RoleId = #{roleid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer roleid);

    @Insert({
        "insert into rbac_role_tb (RoleName, Description, ",
        "CreatedBy, CreatedOn, ",
        "ModifiedBy, ModifiedOn)",
        "values (#{rolename,jdbcType=VARCHAR}, #{description,jdbcType=VARCHAR}, ",
        "#{createdby,jdbcType=VARCHAR}, #{createdon,jdbcType=TIMESTAMP}, ",
        "#{modifiedby,jdbcType=VARCHAR}, #{modifiedon,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="roleid", before=false, resultType=Integer.class)
    int insert(Role record);

    @InsertProvider(type=RoleSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="roleid", before=false, resultType=Integer.class)
    int insertSelective(Role record);

    @Select({
        "select",
        "RoleId, RoleName, Description, CreatedBy, CreatedOn, ModifiedBy, ModifiedOn",
        "from rbac_role_tb",
        "where RoleId = #{roleid,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="RoleId", property="roleid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="RoleName", property="rolename", jdbcType=JdbcType.VARCHAR),
        @Result(column="Description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="CreatedBy", property="createdby", jdbcType=JdbcType.VARCHAR),
        @Result(column="CreatedOn", property="createdon", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="ModifiedBy", property="modifiedby", jdbcType=JdbcType.VARCHAR),
        @Result(column="ModifiedOn", property="modifiedon", jdbcType=JdbcType.TIMESTAMP)
    })
    Role selectByPrimaryKey(Integer roleid);

    @UpdateProvider(type=RoleSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Role record);

    @Update({
        "update rbac_role_tb",
        "set RoleName = #{rolename,jdbcType=VARCHAR},",
          "Description = #{description,jdbcType=VARCHAR},",
          "CreatedBy = #{createdby,jdbcType=VARCHAR},",
          "CreatedOn = #{createdon,jdbcType=TIMESTAMP},",
          "ModifiedBy = #{modifiedby,jdbcType=VARCHAR},",
          "ModifiedOn = #{modifiedon,jdbcType=TIMESTAMP}",
        "where RoleId = #{roleid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Role record);
}