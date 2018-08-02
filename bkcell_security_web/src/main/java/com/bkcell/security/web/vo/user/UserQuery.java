package com.bkcell.security.web.vo.user;


import com.bkcell.security.common.entity.PageQuery;

public class UserQuery extends PageQuery {
    private Integer roleId;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
