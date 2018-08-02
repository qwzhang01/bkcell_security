package com.bkcell.security.web.vo.role;

import java.io.Serializable;

public class AssignPermissionVo implements Serializable {

    private Integer roleid;
    private Integer[] permissionId;

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public Integer[] getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer[] permissionId) {
        this.permissionId = permissionId;
    }
}
