package com.bkcell.security.web.controller;

import com.bkcell.security.common.annotation.Token;
import com.bkcell.security.common.entity.GridPage;
import com.bkcell.security.common.entity.R;
import com.bkcell.security.generator.pojo.Role;
import com.bkcell.security.web.service.PermissionService;
import com.bkcell.security.web.service.RoleService;
import com.bkcell.security.web.vo.role.AssignPermissionVo;
import com.bkcell.security.web.vo.role.RoleQuery;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 控制器：index显示页面，list获取页面数据 form表单 detail详情 save保存表单
 */
@Controller
@RequestMapping(value = "/system")
public class RoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;

    @RequiresPermissions("角色管理")
    @RequestMapping(value = "role", method = RequestMethod.GET)
    public String index(Model model) {
        return "role/index.html";
    }

    @RequiresPermissions("角色管理")
    @RequestMapping(value = "role/list", method = RequestMethod.POST)
    @ResponseBody
    public GridPage<Map<String, Object>> list(RoleQuery query) {
        PageInfo<Map<String, Object>> page = roleService.page(query);
        return new GridPage(page.getTotal(), page.getList());
    }

    @RequiresPermissions(value = {"角色管理-新增", "角色管理-编辑"}, logical = Logical.OR)
    @Token(save = true)
    @RequestMapping(value = "role/form", method = RequestMethod.GET)
    public String form(@RequestParam(value = "roleId", defaultValue = "0") Integer roleId, Model model) {
        if (roleId > 0) {
            Role role = roleService.getById(roleId);
            model.addAttribute(role);
        }
        return "role/form.html";
    }

    @RequiresPermissions(value = {"角色管理-新增", "角色管理-编辑"}, logical = Logical.OR)
    @RequestMapping(value = "role/save", method = RequestMethod.POST)
    @ResponseBody
    @Token(remove = true)
    public R save(Role role) {
        boolean save = roleService.save(role);
        if (save) {
            return R.ok("保存成功");
        }
        return R.error("保存失败");
    }

    @RequestMapping(value = "role/valid/{roleId}")
    @ResponseBody
    public R valid(@PathVariable(value = "roleId") Integer roleId, @RequestParam(value = "param", defaultValue = "") String param) {
        boolean valid = roleService.validName(roleId, param);
        if (valid) {
            return R.validateOk();
        }
        return R.validateNo("该角色已存在。");
    }

    @RequiresPermissions("角色管理-分配权限")
    @Token(save = true)
    @RequestMapping(value = "role/assign/{roleId}", method = RequestMethod.GET)
    public String assign(@PathVariable(value = "roleId") Integer roleId, Model model) {
        model.addAttribute(roleId);
        return "role/permissions.html";
    }

    @RequiresPermissions("角色管理-分配权限")
    @RequestMapping(value = "role/assign/save", method = RequestMethod.POST)
    @ResponseBody
    @Token(remove = true)
    public R assignSave(AssignPermissionVo assignPermissionVo) {
        boolean save = roleService.assign(assignPermissionVo.getRoleid(), assignPermissionVo.getPermissionId());
        if (save) {
            return R.ok("分配成功");
        }
        return R.error("分配失败");
    }

    @RequiresPermissions("角色管理-分配权限")
    @RequestMapping(value = "role/permissions/{roleId}", method = RequestMethod.POST)
    @ResponseBody
    public List<Map<String, Object>> permissions(@PathVariable(value = "roleId") Integer roleId, Model model) {
        List<Map<String, Object>> permissions = permissionService.findByRoleId(roleId);
        return permissions;
    }
}