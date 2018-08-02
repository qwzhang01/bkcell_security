package com.bkcell.security.web.service.impl;

import com.bkcell.security.common.entity.Nav;
import com.bkcell.security.generator.pojo.Permission;
import com.bkcell.security.generator.pojo.Role;
import com.bkcell.security.web.dao.PermissionDao;
import com.bkcell.security.web.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    /**
     * 根据权限信息生成目录
     */
    @Override
    @Cacheable(cacheNames = "PermissionService", key = "'PermissionService:genNav'")
    public List<Nav> findNav() {
        List<Permission> permissions = permissionDao.selectAllPermission();
        List<Permission> collect = permissions.stream()
                .collect(Collectors.groupingBy(Permission::getSubmenu, Collectors.minBy(Comparator.comparing(Permission::getSortno))))
                .values()
                .stream().map(p -> p.get())
                .sorted(Comparator.comparing(Permission::getSortno))
                .collect(Collectors.toList());
        // 一级菜单
        List<Permission> menuLevelOne = collect.stream()
                .collect(Collectors.groupingBy(Permission::getMenu, Collectors.minBy(Comparator.comparing(Permission::getSortno))))
                .values()
                .stream().map(p -> p.get())
                .sorted(Comparator.comparing(Permission::getSortno))
                .collect(Collectors.toList());
        // 二级菜单
        Map<String, List<Permission>> navWithName = collect.stream().collect(Collectors.groupingBy(Permission::getMenu));

        List<Nav> navList = new ArrayList<>();
        menuLevelOne.stream().forEach(p -> {
            List<Nav> navPer = navWithName.get(p.getMenu()).stream().map(n -> new Nav(n)).collect(Collectors.toList());
            Nav nav = new Nav(p, navPer);
            navList.add(nav);
        });
        return navList;
    }

    @Override
    public List<Permission> findByRole(Role role) {
        List<Permission> permissions = permissionDao.selectAuthInfoByRoleId(role.getRoleid());
        return permissions;
    }

    @Override
    public List<Map<String, Object>> findByRoleId(Integer roleId) {
        return permissionDao.selectByRoleId(roleId);
    }
}
