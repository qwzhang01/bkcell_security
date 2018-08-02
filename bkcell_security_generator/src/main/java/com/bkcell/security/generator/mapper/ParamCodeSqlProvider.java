package com.bkcell.security.generator.mapper;

import com.bkcell.security.generator.pojo.ParamCode;
import org.apache.ibatis.jdbc.SQL;

public class ParamCodeSqlProvider {

    public String insertSelective(ParamCode record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("param_code_tb");
        
        if (record.getCode() != null) {
            sql.VALUES("code", "#{code,jdbcType=INTEGER}");
        }
        
        if (record.getName() != null) {
            sql.VALUES("name", "#{name,jdbcType=VARCHAR}");
        }
        
        if (record.getType() != null) {
            sql.VALUES("type", "#{type,jdbcType=VARCHAR}");
        }
        
        if (record.getRemark() != null) {
            sql.VALUES("remark", "#{remark,jdbcType=VARCHAR}");
        }
        
        if (record.getIsLocked() != null) {
            sql.VALUES("is_locked", "#{isLocked,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(ParamCode record) {
        SQL sql = new SQL();
        sql.UPDATE("param_code_tb");
        
        if (record.getCode() != null) {
            sql.SET("code = #{code,jdbcType=INTEGER}");
        }
        
        if (record.getName() != null) {
            sql.SET("name = #{name,jdbcType=VARCHAR}");
        }
        
        if (record.getType() != null) {
            sql.SET("type = #{type,jdbcType=VARCHAR}");
        }
        
        if (record.getRemark() != null) {
            sql.SET("remark = #{remark,jdbcType=VARCHAR}");
        }
        
        if (record.getIsLocked() != null) {
            sql.SET("is_locked = #{isLocked,jdbcType=INTEGER}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}