package com.bkcell.security.shiro;

import com.bkcell.security.common.kit.PropKit;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public RedisSessionDao sessionDao() {
        RedisSessionDao sessionDao = new RedisSessionDao();
        sessionDao.setCacheManager(shiroRedisCacheManager());
        return sessionDao;
    }

    @Bean
    public DefaultWebSessionManager sessionManager() {
        ShiroSessionManager sessionManager = new ShiroSessionManager();
        sessionManager.setSessionDAO(sessionDao());
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        //设置session过期时间为1小时(单位：毫秒)，默认为30分钟
        sessionManager.setGlobalSessionTimeout(PropKit.getInt("spring.redis.session.timeout") * 60 * 1000);
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setCacheManager(shiroRedisCacheManager());
        sessionManager.setSessionValidationSchedulerEnabled(false);
        Cookie sessionIdCookie = sessionManager.getSessionIdCookie();
        // 这里可以扩大cookie的作用域范围
        // sessionIdCookie.setDomain("");
        sessionIdCookie.setPath("/");
        // 这里可以自定义sessionId在cookie中的键
        //sessionIdCookie.setName("csid");
        sessionManager.setSessionIdCookieEnabled(true);
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        return sessionManager;
    }

    /**
     * 缓存管理器，缓存都从这里取
     *
     * @return
     */
    @Bean(name = "shiroRedisCacheManager")
    public ShiroRedisCacheManager shiroRedisCacheManager() {
        ShiroRedisCacheManager shiroRedisCacheManager = new ShiroRedisCacheManager();
        return shiroRedisCacheManager;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(authRealm());
        securityManager.setCacheManager(shiroRedisCacheManager());
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

    @Bean
    public AuthorizingRealm authRealm() {
        Boolean security = PropKit.getBoolean("security", false);
        if (security) {
            AuthorizingRealm authRealm = new AuthRealm();
            authRealm.setCacheManager(shiroRedisCacheManager());
            authRealm.setCachingEnabled(true);
            authRealm.setAuthenticationCachingEnabled(true);
            authRealm.setAuthorizationCachingEnabled(true);
            return authRealm;
        }
        return new NonceRealm();
    }

    /**
     * shiro 的filter链
     *
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, Filter> filterMap = new HashMap<>();
        filterMap.put("authc_kisso", new KissoFilter());
        filterMap.put("shiro_kisso", new KiSsoShiroAuthFilter());
        filterMap.put("logout_kisso", new KiSsoShiroLogoutFilter());
        shiroFilterFactoryBean.setFilters(filterMap);
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        // 配置不会被拦截的链接 顺序判断
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/fonts/**", "anon");
        filterChainDefinitionMap.put("/login/logout", "logout_kisso");
        filterChainDefinitionMap.put("/login/**", "anon");
        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
        //<!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        filterChainDefinitionMap.put("/**", "authc_kisso, shiro_kisso");
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setSuccessUrl("/index");
        // 未授权界面;spring boot 不管用，不造为啥
        // shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }
}
