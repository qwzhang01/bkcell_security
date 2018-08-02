package com.bkcell.security.common.listener;

import cn.hutool.core.exceptions.ExceptionUtil;
import com.bkcell.security.common.aop.TokenContract;
import com.bkcell.security.common.cache.LocalCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
public class RequestUrlListener implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(TokenContract.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() == null) {
            try {
                ApplicationContext applicationContext = event
                        .getApplicationContext();
                RequestMappingHandlerMapping bean = applicationContext
                        .getBean(RequestMappingHandlerMapping.class);
                Set<String> result = new HashSet<String>();
                Map<RequestMappingInfo, HandlerMethod> handlerMethods = bean
                        .getHandlerMethods();
                for (RequestMappingInfo rmi : handlerMethods.keySet()) {
                    PatternsRequestCondition pc = rmi.getPatternsCondition();
                    Set<String> pSet = pc.getPatterns();
                    result.addAll(pSet);
                }
                LocalCache.ACCESS_URL.addAll(result);
            } catch (Exception e) {
                logger.error(ExceptionUtil.stacktraceToString(e));
            }
        }
    }
}