package com.application.ttm.web.controller;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>@Author tangtaiming</p>
 * <p>@Date 2018-12-27</p>
 * <p>@Version 1.0</p>
 **/
@Controller
public class LoginController {

    @RequestMapping("login")
    public String showLoginForm(HttpServletRequest req, Model model) {
        String exceptionClassName = (String) req.getAttribute("shiroLoginFailure");
        String errorValue = null;
        if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
            errorValue = "用户名/密码错误";
        } else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
            errorValue = "用户名/密码错误";
        } else if (null != exceptionClassName) {
            errorValue = "其他错误: " + exceptionClassName;
        }

        model.addAttribute("error", errorValue);
        return "login";
    }

}
