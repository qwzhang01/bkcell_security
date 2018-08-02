package com.bkcell.security.generator.mapper;

import com.bkcell.security.generator.pojo.Org;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface OrgMapper {
    @Delete({
        "delete from global_org_tb",
        "where OrgId = #{orgid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer orgid);

    @Insert({
        "insert into global_org_tb (OrgId, OrgNo, ",
        "OrgName, ParentId, ",
        "LevelCode, Remark, ",
        "CreatedBy, CreatedOn, ",
        "ModifiedBy, ModifiedOn)",
        "values (#{orgid,jdbcType=INTEGER}, #{orgno,jdbcType=VARCHAR}, ",
        "#{orgname,jdbcType=VARCHAR}, #{parentid,jdbcType=INTEGER}, ",
        "#{levelcode,jdbcType=VARCHAR}, #{remark,jdbcType=VARCHAR}, ",
        "#{createdby,jdbcType=VARCHAR}, #{createdon,jdbcType=TIMESTAMP}, ",
        "#{modifiedby,jdbcType=VARCHAR}, #{modifiedon,jdbcType=TIMESTAMP})"
    })
    int insert(Org record);

    @InsertProvider(type=OrgSqlProvider.class, method="insertSelective")
    int insertSelective(Org record);

    @Select({
        "select",
        "OrgId, OrgNo, OrgName, ParentId, LevelCode, Remark, CreatedBy, CreatedOn, ModifiedBy, ",
        "ModifiedOn",
        "from global_org_tb",
        "where OrgId = #{orgid,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="OrgId", property="orgid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="OrgNo", property="orgno", jdbcType=JdbcType.VARCHAR),
        @Result(column="OrgName", property="orgname", jdbcType=JdbcType.VARCHAR),
        @Result(column="ParentId", property="parentid", jdbcType=JdbcType.INTEGER),
        @Result(column="LevelCode", property="levelcode", jdbcType=JdbcType.VARCHAR),
        @Result(column="Remark", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="CreatedBy", property="createdby", jdbcType=JdbcType.VARCHAR),
        @Result(column="CreatedOn", property="createdon", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="ModifiedBy", property="modifiedby", jdbcType=JdbcType.VARCHAR),
        @Result(column="ModifiedOn", property="modifiedon", jdbcType=JdbcType.TIMESTAMP)
    })
    Org selectByPrimaryKey(Integer orgid);

    @UpdateProvider(type=OrgSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Org record);

    @Update({
        "update global_org_tb",
        "set OrgNo = #{orgno,jdbcType=VARCHAR},",
          "OrgName = #{orgname,jdbcType=VARCHAR},",
          "ParentId = #{parentid,jdbcType=INTEGER},",
          "LevelCode = #{levelcode,jdbcType=VARCHAR},",
          "Remark = #{remark,jdbcType=VARCHAR},",
          "CreatedBy = #{createdby,jdbcType=VARCHAR},",
          "CreatedOn = #{createdon,jdbcType=TIMESTAMP},",
          "ModifiedBy = #{modifiedby,jdbcType=VARCHAR},",
          "ModifiedOn = #{modifiedon,jdbcType=TIMESTAMP}",
        "where OrgId = #{orgid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Org record);
}