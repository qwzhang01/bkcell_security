package com.bkcell.security.web.beetl;

import org.beetl.core.GroupTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class BeetlExtConfig {
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private GroupTemplate groupTemplate;

    @PostConstruct
    public void config() {
        Map<String, Object> shared = new HashMap<String, Object>();
        shared.put("title", "BKCELL-SECURITY后台管理系统");
        groupTemplate.setSharedVars(shared);

        groupTemplate.registerFunction("nav", applicationContext.getBean(NavFunction.class));
    }
}