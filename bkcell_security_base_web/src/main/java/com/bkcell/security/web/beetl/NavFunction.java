package com.bkcell.security.web.beetl;

import com.bkcell.security.common.entity.Nav;
import com.bkcell.security.common.kit.RedisKit;
import org.beetl.core.Context;
import org.beetl.core.Function;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NavFunction implements Function {

    @Override
    public Object call(Object[] paras, Context ctx) {
        List<Nav> navs = RedisKit.get("PermissionService:genNav");
        return navs;
    }
}