package com.bkcell.security.generator.mapper;

import com.bkcell.security.generator.pojo.User;
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

public interface UserMapper {
    @Delete({
        "delete from global_user_tb",
        "where UserId = #{userid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer userid);

    @Insert({
        "insert into global_user_tb (UserName, Password, ",
        "PasswordType, RealName, ",
        "PhoneNum, OrgId, ",
        "CreatedBy, CreatedOn, ",
        "ModifiedBy, ModifiedOn)",
        "values (#{username,jdbcType=VARCHAR}, #{password,jdbcType=VARCHAR}, ",
        "#{passwordtype,jdbcType=INTEGER}, #{realname,jdbcType=VARCHAR}, ",
        "#{phonenum,jdbcType=VARCHAR}, #{orgid,jdbcType=INTEGER}, ",
        "#{createdby,jdbcType=VARCHAR}, #{createdon,jdbcType=TIMESTAMP}, ",
        "#{modifiedby,jdbcType=VARCHAR}, #{modifiedon,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="userid", before=false, resultType=Integer.class)
    int insert(User record);

    @InsertProvider(type=UserSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="userid", before=false, resultType=Integer.class)
    int insertSelective(User record);

    @Select({
        "select",
        "UserId, UserName, Password, PasswordType, RealName, PhoneNum, OrgId, CreatedBy, ",
        "CreatedOn, ModifiedBy, ModifiedOn",
        "from global_user_tb",
        "where UserId = #{userid,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="UserId", property="userid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="UserName", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="Password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="PasswordType", property="passwordtype", jdbcType=JdbcType.INTEGER),
        @Result(column="RealName", property="realname", jdbcType=JdbcType.VARCHAR),
        @Result(column="PhoneNum", property="phonenum", jdbcType=JdbcType.VARCHAR),
        @Result(column="OrgId", property="orgid", jdbcType=JdbcType.INTEGER),
        @Result(column="CreatedBy", property="createdby", jdbcType=JdbcType.VARCHAR),
        @Result(column="CreatedOn", property="createdon", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="ModifiedBy", property="modifiedby", jdbcType=JdbcType.VARCHAR),
        @Result(column="ModifiedOn", property="modifiedon", jdbcType=JdbcType.TIMESTAMP)
    })
    User selectByPrimaryKey(Integer userid);

    @UpdateProvider(type=UserSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(User record);

    @Update({
        "update global_user_tb",
        "set UserName = #{username,jdbcType=VARCHAR},",
          "Password = #{password,jdbcType=VARCHAR},",
          "PasswordType = #{passwordtype,jdbcType=INTEGER},",
          "RealName = #{realname,jdbcType=VARCHAR},",
          "PhoneNum = #{phonenum,jdbcType=VARCHAR},",
          "OrgId = #{orgid,jdbcType=INTEGER},",
          "CreatedBy = #{createdby,jdbcType=VARCHAR},",
          "CreatedOn = #{createdon,jdbcType=TIMESTAMP},",
          "ModifiedBy = #{modifiedby,jdbcType=VARCHAR},",
          "ModifiedOn = #{modifiedon,jdbcType=TIMESTAMP}",
        "where UserId = #{userid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(User record);
}