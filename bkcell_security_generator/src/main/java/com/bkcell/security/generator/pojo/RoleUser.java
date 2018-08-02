package com.bkcell.security.generator.pojo;

import java.io.Serializable;

public class RoleUser implements Serializable {
    private Integer roleid;

    private Integer userid;

    private static final long serialVersionUID = 1L;

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
}