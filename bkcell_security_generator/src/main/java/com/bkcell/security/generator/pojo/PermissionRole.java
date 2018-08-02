package com.bkcell.security.generator.pojo;

import java.io.Serializable;

public class PermissionRole implements Serializable {
    private Integer rpid;

    private Integer roleid;

    private Integer permissionid;

    private static final long serialVersionUID = 1L;

    public Integer getRpid() {
        return rpid;
    }

    public void setRpid(Integer rpid) {
        this.rpid = rpid;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public Integer getPermissionid() {
        return permissionid;
    }

    public void setPermissionid(Integer permissionid) {
        this.permissionid = permissionid;
    }
}