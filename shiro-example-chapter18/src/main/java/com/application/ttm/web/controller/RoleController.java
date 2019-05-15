package com.application.ttm.web.controller;

import com.application.ttm.ResponseUtils;
import com.application.ttm.entity.Role;
import com.application.ttm.service.ResourceService;
import com.application.ttm.service.RoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * <p>@Author tangtaiming</p>
 * <p>@Date 2018-12-29</p>
 * <p>@Version 1.0</p>
 **/
@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private ResourceService resourceService;

    @RequiresPermissions("role:view")
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.GET})
    public String list(Model model,
                       @RequestParam(value = "pageNum", defaultValue = "1", required = false) int pageNum,
                       @RequestParam(value = "numPerPage", defaultValue = "20", required = false) int numPerPage) {
        System.out.println("pageNumber: " + pageNum + " numPerPage: " + numPerPage);
        model.addAttribute("roleList", roleService.findList(pageNum, numPerPage));
        return "role/list";
    }

    @RequiresPermissions("role:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String showCreate(Model model) {
        setCommonData(model);
        model.addAttribute("role", new Role());
        model.addAttribute("op", "新增");
        return "role/edit";
    }

    @RequiresPermissions("role:create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public String create(Role role, RedirectAttributes redirectAttributes) {
        System.out.println("role: " + role.toString());
        roleService.createRole(role);
//        redirectAttributes.addFlashAttribute("msg", "新增成功");
        return ResponseUtils.success("/role");
    }

    @RequiresPermissions("role:update")
    @RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
    public String showUpdate(@PathVariable("id") Long id, Model model) {
        setCommonData(model);
        model.addAttribute("role", roleService.findOne(id));
        model.addAttribute("op", "修改");
        return "role/edit";
    }

    @RequiresPermissions("role:update")
    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    @ResponseBody
    public String update(Role role, RedirectAttributes redirectAttributes) {
        roleService.updateRole(role);
//        redirectAttributes.addFlashAttribute("msg", "修改成功");
        return ResponseUtils.success("/role");
    }

    @RequiresPermissions("role:delete")
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String showDelete(@PathVariable("id") Long id, Model model) {
        setCommonData(model);
        model.addAttribute("role", roleService.findOne(id));
        model.addAttribute("op", "删除");
        return "role/edit";
    }

    @RequiresPermissions("role:delete")
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
    @ResponseBody
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        roleService.deleteRole(id);
//        redirectAttributes.addFlashAttribute("msg", "删除成功");
        return ResponseUtils.success("/role", "/role");
    }

    private void setCommonData(Model model) {
        model.addAttribute("resourceList", resourceService.findAll());
    }

}
