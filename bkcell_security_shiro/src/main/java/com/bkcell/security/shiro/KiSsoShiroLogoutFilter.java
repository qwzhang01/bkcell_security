package com.bkcell.security.shiro;

import com.baomidou.kisso.SSOHelper;
import org.apache.shiro.web.filter.authc.LogoutFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class KiSsoShiroLogoutFilter extends LogoutFilter {

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        SSOHelper.clearLogin((HttpServletRequest) request, (HttpServletResponse) response);
        return super.preHandle(request, response);
    }
}
