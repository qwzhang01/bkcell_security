package com.bkcell.security.web.service;

import com.bkcell.security.generator.pojo.Role;
import com.bkcell.security.web.vo.role.RoleQuery;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface RoleService {
    List<Role> findByUserId(Integer userid);

    List<Map<Integer, Object>> findUserRole(Integer userId);

    List<Map<Integer, String>> list();

    PageInfo<Map<String, Object>> page(RoleQuery query);

    Role getById(Integer roleId);

    boolean save(Role role);

    boolean validName(Integer roleId, String roleName);

    boolean assign(Integer roleId, Integer[] permissionId);
}
