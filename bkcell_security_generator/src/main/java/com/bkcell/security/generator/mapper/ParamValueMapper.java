package com.bkcell.security.generator.mapper;

import com.bkcell.security.generator.pojo.ParamValue;
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

public interface ParamValueMapper {
    @Delete({
        "delete from param_value_tb",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into param_value_tb (Name, Value, ",
        "Remark)",
        "values (#{name,jdbcType=VARCHAR}, #{value,jdbcType=VARCHAR}, ",
        "#{remark,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(ParamValue record);

    @InsertProvider(type=ParamValueSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(ParamValue record);

    @Select({
        "select",
        "Id, Name, Value, Remark",
        "from param_value_tb",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="Id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="Name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="Value", property="value", jdbcType=JdbcType.VARCHAR),
        @Result(column="Remark", property="remark", jdbcType=JdbcType.VARCHAR)
    })
    ParamValue selectByPrimaryKey(Integer id);

    @UpdateProvider(type=ParamValueSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(ParamValue record);

    @Update({
        "update param_value_tb",
        "set Name = #{name,jdbcType=VARCHAR},",
          "Value = #{value,jdbcType=VARCHAR},",
          "Remark = #{remark,jdbcType=VARCHAR}",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(ParamValue record);
}