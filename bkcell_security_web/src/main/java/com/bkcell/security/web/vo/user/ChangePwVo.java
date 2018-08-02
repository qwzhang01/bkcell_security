package com.bkcell.security.web.vo.user;

import java.io.Serializable;

public class ChangePwVo implements Serializable {

    private String oldPw;
    private String newPw;
    private String cfPw;

    public String getOldPw() {
        return oldPw;
    }

    public void setOldPw(String oldPw) {
        this.oldPw = oldPw;
    }

    public String getNewPw() {
        return newPw;
    }

    public void setNewPw(String newPw) {
        this.newPw = newPw;
    }

    public String getCfPw() {
        return cfPw;
    }

    public void setCfPw(String cfPw) {
        this.cfPw = cfPw;
    }
}
