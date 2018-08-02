package com.bkcell.security.generator.mapper;

import com.bkcell.security.generator.pojo.ParamValue;
import org.apache.ibatis.jdbc.SQL;

public class ParamValueSqlProvider {

    public String insertSelective(ParamValue record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("param_value_tb");
        
        if (record.getName() != null) {
            sql.VALUES("Name", "#{name,jdbcType=VARCHAR}");
        }
        
        if (record.getValue() != null) {
            sql.VALUES("Value", "#{value,jdbcType=VARCHAR}");
        }
        
        if (record.getRemark() != null) {
            sql.VALUES("Remark", "#{remark,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(ParamValue record) {
        SQL sql = new SQL();
        sql.UPDATE("param_value_tb");
        
        if (record.getName() != null) {
            sql.SET("Name = #{name,jdbcType=VARCHAR}");
        }
        
        if (record.getValue() != null) {
            sql.SET("Value = #{value,jdbcType=VARCHAR}");
        }
        
        if (record.getRemark() != null) {
            sql.SET("Remark = #{remark,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("Id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}