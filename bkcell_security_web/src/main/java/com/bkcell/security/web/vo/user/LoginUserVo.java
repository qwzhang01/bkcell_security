package com.bkcell.security.web.vo.user;

import java.io.Serializable;

public class LoginUserVo implements Serializable {
    private String username;
    private String password;
    private String safecode_iput;
    private String returnUrl;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSafecode_iput() {
        return safecode_iput;
    }

    public void setSafecode_iput(String safecode_iput) {
        this.safecode_iput = safecode_iput;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }
}
