package com.bkcell.security.shiro;

import com.bkcell.security.generator.pojo.Permission;
import com.bkcell.security.generator.pojo.Role;
import com.bkcell.security.generator.pojo.User;

import java.util.List;

public interface AuthService {
    User getByUserName(String username);

    List<Role> findByUserId(Integer userid);

    List<Permission> findByRole(Role role);
}
