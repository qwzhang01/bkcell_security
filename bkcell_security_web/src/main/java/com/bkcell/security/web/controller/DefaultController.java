package com.bkcell.security.web.controller;

import com.bkcell.security.common.entity.R;
import com.bkcell.security.generator.pojo.User;
import com.bkcell.security.web.service.UserService;
import com.bkcell.security.web.vo.user.ChangePwVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class DefaultController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String index(HttpServletRequest request) {
        return "common/index.html";
    }

    @RequestMapping(value = "/changepw", method = RequestMethod.GET)
    public String changepw() {
        return "common/changepw.html";
    }

    @RequestMapping(value = "/validpwd", method = RequestMethod.POST)
    @ResponseBody
    public R validpwd(@RequestParam(value = "param") String pw, HttpServletRequest request) {
        String username = (String) request.getSession().getAttribute("username");
        boolean valid = userService.validPw(username, pw);
        if (valid) {
            return R.validateOk();
        }
        return R.validateNo("密码输入错误！");
    }

    @RequestMapping(value = "/changepw/save", method = RequestMethod.POST)
    @ResponseBody
    public R saveChangepw(ChangePwVo changePwVo, HttpServletRequest request) {
        String username = (String) request.getSession().getAttribute("username");
        boolean valid = userService.validPw(username, changePwVo.getOldPw());
        if (!valid) {
            return R.error("原密码错误！");
        }
        User user = userService.getByUserName(username);
        boolean changePw = userService.changePw(user.getUserid(), changePwVo.getNewPw());
        if (changePw) {
            return R.ok("修改成功！");
        }
        return R.error("修改失败！");
    }

    @RequestMapping("/403")
    public String unauthorized() {
        return "common/403.html";
    }

    @RequestMapping("/404")
    public String notfond() {
        return "common/404.html";
    }
}