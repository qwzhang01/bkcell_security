package com.bkcell.security.web.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.kisso.SSOHelper;
import com.baomidou.kisso.SSOToken;
import com.baomidou.kisso.common.IpHelper;
import com.bkcell.security.common.entity.Nav;
import com.bkcell.security.common.kit.CaptchaKit;
import com.bkcell.security.generator.pojo.User;
import com.bkcell.security.shiro.ShiroCrypt;
import com.bkcell.security.shiro.ShiroMethod;
import com.bkcell.security.web.service.PermissionService;
import com.bkcell.security.web.service.UserService;
import com.bkcell.security.web.vo.user.LoginUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private PermissionService permissionService;
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@RequestParam(value = "ReturnURL", defaultValue = "") String retuenUrl, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        List<Nav> navs = permissionService.findNav();
        if(navs == null){
            return "common/login.html";
        }
        if (ShiroMethod.authenticated()) {
            return "redirect:/";
        }
        return "common/login.html";
    }

    @RequestMapping(value = "/login/check", method = RequestMethod.POST)
    public String login(@RequestParam(value = "ReturnURL", defaultValue = "") String retuenUrl, LoginUserVo user, RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response) {
        if (StrUtil.hasBlank(user.getUsername(), user.getPassword(), user.getSafecode_iput())) {
            redirectAttributes.addFlashAttribute("msg", "用户名或密码验证码都均能为空");
            return "redirect:/login";
        }
        if (!CaptchaKit.validate(user.getSafecode_iput(), request)) {
            redirectAttributes.addFlashAttribute("msg", "验证码错误");
            return "redirect:/login";
        }
        User checkUser = userService.getByUserName(user.getUsername());
        if (checkUser == null) {
            redirectAttributes.addFlashAttribute("msg", "用户不存在，请核对后重新登录");
            return "redirect:/login";
        }
        String encrypt = ShiroCrypt.encrypt(user.getPassword());
        if (!checkUser.getPassword().equals(encrypt)) {
            redirectAttributes.addFlashAttribute("msg", "密码错误，请核对后重新登录");
            return "redirect:/login";
        }

        if (userService.login(checkUser.getUsername(), checkUser.getPassword())) {
            HttpSession session = request.getSession();
            session.setAttribute("username", checkUser.getUsername());
            session.setAttribute("fullName", checkUser.getRealname());

            SSOToken ssoToken = new SSOToken();
            ssoToken.setUid(user.getUsername());
            ssoToken.setApp("oms");
            ssoToken.setIp(IpHelper.getIpAddr(request));
            ssoToken.setData(request.getSession().getId());
            SSOHelper.setSSOCookie(request, response, ssoToken, false);
            return "redirect:/";
        }
        redirectAttributes.addFlashAttribute("msg", "登陆失败");
        return "redirect:/login";
    }

    @RequestMapping(value = "/login/image", method = RequestMethod.GET)
    public void image(HttpServletRequest request, HttpServletResponse response) {
        CaptchaKit.renderCapcha(request, response, 4);
    }


}