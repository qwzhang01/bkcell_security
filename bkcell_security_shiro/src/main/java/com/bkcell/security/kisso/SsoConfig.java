package com.bkcell.security.kisso;

import com.baomidou.kisso.web.WebKissoConfigurer;
import com.baomidou.kisso.web.interceptor.SSOSpringInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class SsoConfig  extends WebMvcConfigurerAdapter {
    /**
     * 自定义拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SSOSpringInterceptor()).addPathPatterns("/**").excludePathPatterns("/login/**");
        super.addInterceptors(registry);
    }

    /**
     * 注入Kisso
     *
     * @return
     */
    @Bean(initMethod = "initKisso")
    public WebKissoConfigurer webKissoConfigurer() {
        WebKissoConfigurer webKissoConfigurer = new WebKissoConfigurer("sso.properties");
        return webKissoConfigurer;
    }
}