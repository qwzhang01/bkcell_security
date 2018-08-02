package com.bkcell.security.web.config;

import com.bkcell.security.web.event.EventKit;
import com.bkcell.security.web.interceptor.NavInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 应用手动注入的bean配置类
 */
@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private NavInterceptor navInterceptor;

    /**
     * 自定义拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(navInterceptor).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

    @Bean
    public EventKit eventKit(){
        return new EventKit();
    }
}