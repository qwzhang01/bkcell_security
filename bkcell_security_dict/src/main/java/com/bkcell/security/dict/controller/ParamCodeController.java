package com.bkcell.security.dict.controller;

import com.bkcell.security.common.entity.GridPage;
import com.bkcell.security.common.entity.PageQuery;
import com.bkcell.security.dict.service.ParamCodeService;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/param")
public class ParamCodeController {
    @Autowired
    private ParamCodeService paramCodeService;

    @RequiresPermissions("用户管理")
    @RequestMapping(value = "code", method = RequestMethod.GET)
    public String index(Model model) {
        return "p_code/index.html";
    }

    @RequiresPermissions("用户管理")
    @RequestMapping(value = "code/list", method = RequestMethod.POST)
    @ResponseBody
    public GridPage<Map<String, Object>> list(PageQuery query) {
        PageInfo<Map<String, Object>> page = paramCodeService.page(query);
        return new GridPage(page.getTotal(), page.getList());
    }
}
