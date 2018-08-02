package com.bkcell.security.web.service;


import com.bkcell.security.common.entity.Nav;
import com.bkcell.security.generator.pojo.Permission;
import com.bkcell.security.generator.pojo.Role;

import java.util.List;
import java.util.Map;

public interface PermissionService {

    /**
     * 根据角色查询权限信息
     *
     * @param roleId
     * @return
     */
    List<Map<String, Object>> findByRoleId(Integer roleId);

    /**
     * 根据权限信息生成目录
     *
     * @return
     */
    List<Nav> findNav();

    List<Permission> findByRole(Role role);
}
