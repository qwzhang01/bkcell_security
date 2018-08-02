package com.bkcell.security.generator.pojo;

import java.io.Serializable;

public class Permission implements Serializable {
    private Integer permissionid;

    private String menu;

    private String submenu;

    private String operate;

    private String sortno;

    private String menuicon;

    private String menuflag;

    private String menuhost;

    private String menuroute;

    private static final long serialVersionUID = 1L;

    public Integer getPermissionid() {
        return permissionid;
    }

    public void setPermissionid(Integer permissionid) {
        this.permissionid = permissionid;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu == null ? null : menu.trim();
    }

    public String getSubmenu() {
        return submenu;
    }

    public void setSubmenu(String submenu) {
        this.submenu = submenu == null ? null : submenu.trim();
    }

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate == null ? null : operate.trim();
    }

    public String getSortno() {
        return sortno;
    }

    public void setSortno(String sortno) {
        this.sortno = sortno == null ? null : sortno.trim();
    }

    public String getMenuicon() {
        return menuicon;
    }

    public void setMenuicon(String menuicon) {
        this.menuicon = menuicon == null ? null : menuicon.trim();
    }

    public String getMenuflag() {
        return menuflag;
    }

    public void setMenuflag(String menuflag) {
        this.menuflag = menuflag == null ? null : menuflag.trim();
    }

    public String getMenuhost() {
        return menuhost;
    }

    public void setMenuhost(String menuhost) {
        this.menuhost = menuhost == null ? null : menuhost.trim();
    }

    public String getMenuroute() {
        return menuroute;
    }

    public void setMenuroute(String menuroute) {
        this.menuroute = menuroute == null ? null : menuroute.trim();
    }
}