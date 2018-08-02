package com.bkcell.security.web.shiro;

import com.bkcell.security.generator.pojo.Permission;
import com.bkcell.security.generator.pojo.Role;
import com.bkcell.security.generator.pojo.User;
import com.bkcell.security.shiro.AuthService;
import com.bkcell.security.web.service.PermissionService;
import com.bkcell.security.web.service.RoleService;
import com.bkcell.security.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserService userService;
    @Autowired
    @Lazy
    private RoleService roleService;
    @Autowired
    @Lazy
    private PermissionService permissionService;

    @Override
    public User getByUserName(String username) {
        return userService.getByUserName(username);
    }

    @Override
    public List<Role> findByUserId(Integer userid) {
        return roleService.findByUserId(userid);
    }

    @Override
    public List<Permission> findByRole(Role role) {
        return permissionService.findByRole(role);
    }
}
