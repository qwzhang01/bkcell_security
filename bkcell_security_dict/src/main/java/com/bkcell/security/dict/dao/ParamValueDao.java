package com.bkcell.security.dict.dao;

import com.bkcell.security.common.entity.PageQuery;
import com.bkcell.security.dict.dao.sql.ParamValueSql;
import com.bkcell.security.generator.mapper.ParamValueMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

@Mapper
public interface ParamValueDao extends ParamValueMapper {

    @SelectProvider(type = ParamValueSql.class, method = "selectParamValueSql")
    List<Map<String,Object>> selectByQuery(PageQuery query);
}
