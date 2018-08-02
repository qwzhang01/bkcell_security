package com.bkcell.security.generator.mapper;

import com.bkcell.security.generator.pojo.ParamCode;
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

public interface ParamCodeMapper {
    @Delete({
        "delete from param_code_tb",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into param_code_tb (code, name, ",
        "type, remark, is_locked)",
        "values (#{code,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
        "#{type,jdbcType=VARCHAR}, #{remark,jdbcType=VARCHAR}, #{isLocked,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(ParamCode record);

    @InsertProvider(type=ParamCodeSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(ParamCode record);

    @Select({
        "select",
        "id, code, name, type, remark, is_locked",
        "from param_code_tb",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="code", property="code", jdbcType=JdbcType.INTEGER),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_locked", property="isLocked", jdbcType=JdbcType.INTEGER)
    })
    ParamCode selectByPrimaryKey(Integer id);

    @UpdateProvider(type=ParamCodeSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(ParamCode record);

    @Update({
        "update param_code_tb",
        "set code = #{code,jdbcType=INTEGER},",
          "name = #{name,jdbcType=VARCHAR},",
          "type = #{type,jdbcType=VARCHAR},",
          "remark = #{remark,jdbcType=VARCHAR},",
          "is_locked = #{isLocked,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(ParamCode record);
}