package com.bkcell.security.shiro;

import cn.hutool.core.util.StrUtil;
import com.baomidou.kisso.SSOHelper;
import com.baomidou.kisso.SSOToken;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class KissoFilter extends AccessControlFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String username = (String) httpServletRequest.getSession().getAttribute("username");
        return StrUtil.isNotBlank(username);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        // HttpServletRequest httpRequest = (HttpServletRequest)request;
        // Cookie[] cookies = httpRequest.getCookies();
        SSOToken ssoToken = SSOHelper.getToken((HttpServletRequest) request);
        if (ssoToken != null && ssoToken.getApp().equals("oms")) {
            Cookie cookie = new Cookie("JSESSIONID", ssoToken.getData());
            cookie.setPath("/");
            ((HttpServletResponse) response).addCookie(cookie);
        }
        return true;
    }
}