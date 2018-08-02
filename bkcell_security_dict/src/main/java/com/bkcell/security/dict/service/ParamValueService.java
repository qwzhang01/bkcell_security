package com.bkcell.security.dict.service;

import com.bkcell.security.common.entity.PageQuery;
import com.github.pagehelper.PageInfo;

import java.util.Map;

public interface ParamValueService {
    PageInfo<Map<String,Object>> page(PageQuery query);
}
