package com.application.ttm.web.controller;

import com.application.ttm.ResponseUtils;
import com.application.ttm.entity.User;
import com.application.ttm.service.RoleService;
import com.application.ttm.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * <p>@Author tangtaiming</p>
 * <p>@Date 2018-12-28</p>
 * <p>@Version 1.0</p>
 **/
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @RequiresPermissions("user:view")
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
    public String list(Model model) {
        model.addAttribute("userList", userService.findAll());
        return "user/list";
    }

    @RequiresPermissions("user:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String showCreate(Model model) {
        setCommonData(model);
        model.addAttribute("user", new User());
        model.addAttribute("op", "新增");
        return "user/edit";
    }

    @RequiresPermissions("user:create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public String create(User user) {
        System.out.println("user: " + user.toString());
//        redirectAttributes.addFlashAttribute("msg", "新增成功");
        userService.createUser(user);
        return ResponseUtils.success("/user");
    }

    @RequiresPermissions("user:update")
    @RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
    public String showUpdate(@PathVariable("id") Long id, Model model) {
        setCommonData(model);
        model.addAttribute("user", userService.findOne(id));
        model.addAttribute("op", "修改");
        return "user/edit";
    }

    @RequiresPermissions("user:update")
    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    @ResponseBody
    public String update(User user) {
        userService.updateUser(user);
//        redirectAttributes.addFlashAttribute("msg", "修改成功");
        return ResponseUtils.success("/user");
    }

    @RequiresPermissions("user:delete")
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String showDelete(@PathVariable("id") Long id, Model model) {
        setCommonData(model);
        model.addAttribute("user", userService.findOne(id));
        model.addAttribute("op", "删除");
        return "user/edit";
    }

    @RequiresPermissions("user:delete")
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
    @ResponseBody
    public String delete(@PathVariable("id") Long id) {
        userService.deleteUser(id);
//        redirectAttributes.addFlashAttribute("msg", "删除成功");
        return ResponseUtils.success("/user", "/user");
    }

    @RequiresPermissions("user:update")
    @RequestMapping(value = "/{id}/changePassword", method = RequestMethod.GET)
    public String showChangPassword(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.findOne(id));
        model.addAttribute("op", "修改密码");
        return "user/changePassword";
    }

    @RequiresPermissions("user:update")
    @RequestMapping(value = "/{id}/changePassword", method = RequestMethod.POST)
    @ResponseBody
    public String changPassword(@PathVariable("id") Long id, String newPassword) {
        userService.changePassword(id, newPassword);
//        redirectAttributes.addFlashAttribute("msg", "修改密码成功");
        return ResponseUtils.success("/user");
    }

    private void setCommonData(Model model) {
        model.addAttribute("roleList", roleService.findAll());
    }


}
