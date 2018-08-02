package com.bkcell.security.common.entity;

import com.bkcell.security.generator.pojo.Permission;

import java.io.Serializable;
import java.util.List;

/**
 * 目录
 */
public class Nav implements Serializable {
    private static final long serialVersionUID = 1L;

    private String menuName;
    private String menuHref;
    private String menuIcon;
    private String menuControllerAb;
    private String[] childMenuControllerAb;
    private List<Nav> childMenu;

    /**
     * 父目录的初始化
     *
     * @param permission
     */
    public Nav(Permission permission, List<Nav> childMenu) {
        this.menuName = permission.getMenu();
        this.menuHref = permission.getMenuhost() + permission.getMenuroute();
        this.menuIcon = permission.getMenuicon();
        this.menuControllerAb = permission.getMenuflag();
        if (childMenu != null && childMenu.size() > 0) {
            this.menuHref = "#";
            this.childMenu = childMenu;
            this.childMenuControllerAb = childMenu.stream().map(p -> p.menuControllerAb).toArray(size -> new String[size]);
        }
    }

    /**
     * 子目录的初始化
     *
     * @param permission
     */
    public Nav(Permission permission) {
        this.menuName = permission.getSubmenu();
        this.menuHref = permission.getMenuhost() + permission.getMenuroute();
        this.menuIcon = permission.getMenuicon();
        this.menuControllerAb = permission.getMenuflag();
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuHref() {
        return menuHref;
    }

    public void setMenuHref(String menuHref) {
        this.menuHref = menuHref;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    public String getMenuControllerAb() {
        return menuControllerAb;
    }

    public void setMenuControllerAb(String menuControllerAb) {
        this.menuControllerAb = menuControllerAb;
    }

    public String[] getChildMenuControllerAb() {
        return childMenuControllerAb;
    }

    public void setChildMenuControllerAb(String[] childMenuControllerAb) {
        this.childMenuControllerAb = childMenuControllerAb;
    }

    public List<Nav> getChildMenu() {
        return childMenu;
    }

    public void setChildMenu(List<Nav> childMenu) {
        this.childMenu = childMenu;
    }
}
