package com.bkcell.security.generator.mapper;

import com.bkcell.security.generator.pojo.Org;
import org.apache.ibatis.jdbc.SQL;

public class OrgSqlProvider {

    public String insertSelective(Org record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("global_org_tb");
        
        if (record.getOrgid() != null) {
            sql.VALUES("OrgId", "#{orgid,jdbcType=INTEGER}");
        }
        
        if (record.getOrgno() != null) {
            sql.VALUES("OrgNo", "#{orgno,jdbcType=VARCHAR}");
        }
        
        if (record.getOrgname() != null) {
            sql.VALUES("OrgName", "#{orgname,jdbcType=VARCHAR}");
        }
        
        if (record.getParentid() != null) {
            sql.VALUES("ParentId", "#{parentid,jdbcType=INTEGER}");
        }
        
        if (record.getLevelcode() != null) {
            sql.VALUES("LevelCode", "#{levelcode,jdbcType=VARCHAR}");
        }
        
        if (record.getRemark() != null) {
            sql.VALUES("Remark", "#{remark,jdbcType=VARCHAR}");
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

    public String updateByPrimaryKeySelective(Org record) {
        SQL sql = new SQL();
        sql.UPDATE("global_org_tb");
        
        if (record.getOrgno() != null) {
            sql.SET("OrgNo = #{orgno,jdbcType=VARCHAR}");
        }
        
        if (record.getOrgname() != null) {
            sql.SET("OrgName = #{orgname,jdbcType=VARCHAR}");
        }
        
        if (record.getParentid() != null) {
            sql.SET("ParentId = #{parentid,jdbcType=INTEGER}");
        }
        
        if (record.getLevelcode() != null) {
            sql.SET("LevelCode = #{levelcode,jdbcType=VARCHAR}");
        }
        
        if (record.getRemark() != null) {
            sql.SET("Remark = #{remark,jdbcType=VARCHAR}");
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
        
        sql.WHERE("OrgId = #{orgid,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}