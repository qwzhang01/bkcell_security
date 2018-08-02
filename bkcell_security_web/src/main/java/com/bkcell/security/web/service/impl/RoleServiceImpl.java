package com.bkcell.security.web.service.impl;

import com.bkcell.security.generator.pojo.Role;
import com.bkcell.security.web.dao.RoleDao;
import com.bkcell.security.web.service.RoleService;
import com.bkcell.security.web.vo.role.RoleQuery;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> findByUserId(Integer userid) {
        return roleDao.selectByUserId(userid);
    }

    @Override
    public List<Map<Integer, Object>> findUserRole(Integer userId) {
        return roleDao.selectUserRole(userId);
    }

    @Override
    public List<Map<Integer, String>> list() {
        return roleDao.selectRole();
    }

    @Override
    public PageInfo<Map<String, Object>> page(RoleQuery query) {
        PageHelper.startPage(query.getPageNumber(), query.getPageSize());
        List<Map<String, Object>> roles = roleDao.selectByQuery(query);
        return new PageInfo<>(roles);
    }

    @Override
    public Role getById(Integer roleId) {
        Role role = roleDao.selectByPrimaryKey(roleId);
        return role;
    }

    @Override
    public boolean save(Role role) {
        Integer roleid = role.getRoleid();
        role.setModifiedon(new Date());
        if (roleid != null && roleid != 0) {
            return roleDao.updateByPrimaryKeySelective(role) > 0;
        }
        role.setCreatedon(new Date());
        return roleDao.insertSelective(role) > 0;
    }

    @Override
    public boolean validName(Integer roleId, String roleName) {
        int count = roleDao.validRoleName(roleName, roleId);
        return count == 0;
    }

    @Override
    @Transactional
    public boolean assign(Integer roleId, Integer[] permissionId) {
        roleDao.deletePermission(roleId);
        if (permissionId != null && permissionId.length > 0) {
            roleDao.insertRolePermission(roleId, permissionId);
            return true;
        }
        return true;
    }
}
