package com.bkcell.security.web.controller;

import com.bkcell.security.common.annotation.Token;
import com.bkcell.security.common.entity.GridPage;
import com.bkcell.security.common.entity.R;
import com.bkcell.security.generator.pojo.User;
import com.bkcell.security.web.service.RoleService;
import com.bkcell.security.web.service.UserService;
import com.bkcell.security.web.vo.user.UserQuery;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

/**
 * 控制器：index显示页面，list获取页面数据 form表单 detail详情 save保存表单
 */
@Controller
@RequestMapping(value = "/system")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @RequiresPermissions("用户管理")
    @RequestMapping(value = "user", method = RequestMethod.GET)
    public String index(Model model) {
        List<Map<Integer, String>> list = roleService.list();
        model.addAttribute("roles", list);
        return "user/index.html";
    }

    @RequiresPermissions("用户管理")
    @RequestMapping(value = "user/list", method = RequestMethod.POST)
    @ResponseBody
    public GridPage<Map<String, Object>> list(UserQuery query) {
        PageInfo<Map<String, Object>> page = userService.page(query);
        return new GridPage(page.getTotal(), page.getList());
    }

    @RequiresPermissions(value = {"用户管理-新增", "用户管理-编辑"}, logical = Logical.OR)
    @Token(save = true)
    @RequestMapping(value = "user/form", method = RequestMethod.GET)
    public String form(@RequestParam(value = "userId", defaultValue = "0") Integer userId, Model model) {
        if (userId > 0) {
            User user = userService.getById(userId);
            model.addAttribute("user", user);
        }
        List<Map<Integer, Object>> roles = roleService.findUserRole(userId);
        model.addAttribute("roles", roles);
        return "user/form.html";
    }

    @RequiresPermissions(value = {"用户管理-新增", "用户管理-编辑"}, logical = Logical.OR)
    @RequestMapping(value = "user/save", method = RequestMethod.POST)
    @ResponseBody
    @Token(remove = true)
    public R save(User user, Integer[] roleId) {
        if (!userService.validUserName(user.getUserid(), user.getUsername())) {
            R.validateNo("该用户已存在。");
        }
        boolean save = userService.save(user, roleId);
        if (save) {
            return R.ok("保存成功");
        }
        return R.error("保存失败");
    }

    @RequiresPermissions("用户管理-重置密码")
    @RequestMapping(value = "user/initpw/{userId}")
    public String initpw(@PathVariable(value = "userId") Integer userId, RedirectAttributes redirectAttributes) {
        if (userId == null || userId == 0) {
            redirectAttributes.addFlashAttribute("msg", "未指定要重置的用户！");
            return "redirect:/system/user";
        }
        boolean initpw = userService.changePw(userId, "000000");
        if (initpw) {
            redirectAttributes.addFlashAttribute("msg", "重置成功，该用户登录密码已设置为000000！");
        } else {
            redirectAttributes.addFlashAttribute("msg", "重置失败！");
        }
        return "redirect:/system/user";
    }

    @RequestMapping(value = "user/valid/{userId}")
    @ResponseBody
    public R valid(@PathVariable(value = "userId") Integer userId, @RequestParam(value = "param", defaultValue = "") String param) {
        boolean valid = userService.validUserName(userId, param);
        if (valid) {
            return R.validateOk();
        }
        return R.validateNo("该用户已存在。");
    }

    @RequiresPermissions("用户管理-删除")
    @RequestMapping(value = "user/delete/{userId}")
    public String delete(@PathVariable(value = "userId") Integer userId, RedirectAttributes redirectAttributes) {
        if (userId == null || userId == 0) {
            redirectAttributes.addFlashAttribute("msg", "参数错误，请重试！");
            return "redirect:/system/user";
        }
        boolean delete = userService.deleteById(userId);
        if (delete) {
            redirectAttributes.addFlashAttribute("msg", "删除成功!");
        } else {
            redirectAttributes.addFlashAttribute("msg", "删除失败，请重试！");
        }
        return "redirect:/system/user";
    }
}