package com.bkcell.security.shiro;

import cn.hutool.core.util.StrUtil;
import com.bkcell.security.generator.pojo.Permission;
import com.bkcell.security.generator.pojo.Role;
import com.bkcell.security.generator.pojo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

public class AuthRealm extends AuthorizingRealm {

    @Autowired
    @Lazy
    private AuthService authService;

    /**
     * 认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User user = authService.getByUserName(token.getUsername());
        if (user != null) {
            return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), getName());
        }
        return null;
    }

    /**
     * 授权
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 因为非正常退出，即没有显式调用 SecurityUtils.getSubject().logout()
        // (可能是关闭浏览器，或超时)，但此时缓存依旧存在(principals)，所以会自己跑到授权方法里。
        if (!SecurityUtils.getSubject().isAuthenticated()) {
            doClearCache(principals);
            SecurityUtils.getSubject().logout();
            return null;
        }

        if (principals == null) {
            throw new AuthorizationException("parameters principals is null");
        }

        String loginName = (String) principals.fromRealm(getName()).iterator().next();
        User user = authService.getByUserName(loginName);
        if (user != null) {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            List<Role> roles = authService.findByUserId(user.getUserid());
            if (roles != null && roles.size() > 0) {
                for (Role role : roles) {
                    info.addRole(role.getRolename());
                    info.addStringPermissions(genAuthorizationInfo(role));
                }
            }
            return info;
        }
        return null;
    }

    @PostConstruct
    public void initCredentialsMatcher() {
        ShiroCredentialsMatcher matcher = new ShiroCredentialsMatcher();
        setCredentialsMatcher(matcher);
    }

    private List<String> genAuthorizationInfo(Role role) {
        List<Permission> permissions = authService.findByRole(role);
        List<String> permissionResult = new ArrayList<String>();
        for (Permission record : permissions) {
            String menu = record.getMenu();
            if (!permissionResult.contains(menu)) {
                permissionResult.add(menu);
            }
            String submenu = record.getSubmenu();
            String permission = "";
            if (StrUtil.isNotBlank(submenu)) {
                if (!permissionResult.contains(submenu)) {
                    permissionResult.add(submenu);
                }
                permission = submenu + "-";
            } else {
                permission = menu + "-";
            }
            permissionResult.add(permission + record.getOperate());
        }

        System.out.println("aaa i am running again...........");

        return permissionResult;
    }
}