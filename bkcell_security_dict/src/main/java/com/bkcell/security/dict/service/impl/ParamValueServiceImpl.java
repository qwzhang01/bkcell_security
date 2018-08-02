package com.bkcell.security.dict.service.impl;

import com.bkcell.security.common.entity.PageQuery;
import com.bkcell.security.dict.dao.ParamCodeDao;
import com.bkcell.security.dict.dao.ParamValueDao;
import com.bkcell.security.dict.service.ParamValueService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ParamValueServiceImpl implements ParamValueService {
    @Autowired
    private ParamValueDao paramValueDao;

    @Override
    public PageInfo<Map<String, Object>> page(PageQuery query) {
        PageHelper.startPage(query.getPageNumber(), query.getPageSize());
        List<Map<String, Object>> users = paramValueDao.selectByQuery(query);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(users);
        return pageInfo;
    }
}
