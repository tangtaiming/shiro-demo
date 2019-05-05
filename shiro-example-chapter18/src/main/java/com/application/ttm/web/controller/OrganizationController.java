package com.application.ttm.web.controller;

import com.application.ttm.JsonUtils;
import com.application.ttm.ResponseUtils;
import com.application.ttm.entity.Organization;
import com.application.ttm.service.OrganizationService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * <p>@Author tangtaiming</p>
 * <p>@Date 2019-01-07</p>
 * <p>@Version 1.0</p>
 **/
@Controller
@RequestMapping("/organization")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @RequiresPermissions("organization:view")
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
    public String index(Model model) {
        model.addAttribute("organizationList", organizationService.findAll());
        return "organization/index";
    }

    @RequiresPermissions("organization:view")
    @RequestMapping(value = "/tree", method = RequestMethod.GET)
    public String showTree(Model model) {
        model.addAttribute("organizationList", organizationService.findAll());
        return "organization/tree";
    }

    @RequiresPermissions("organization:create")
    @RequestMapping(value = "/{parentId}/appendChild", method = RequestMethod.GET)
    public String showAppendChildForm(@PathVariable("parentId") Long parentId, Model model) {
        Organization parent = organizationService.findOne(parentId);
        model.addAttribute("parent", parent);
        Organization child = new Organization();
        child.setParentId(parentId);
        child.setParentIds(parent.makeSelfAsParentIds());
        model.addAttribute("child", child);
        model.addAttribute("op", "新增");
        return "organization/dialogAppendChild";
    }

    @RequiresPermissions("organization:create")
    @RequestMapping(value = "/{parentId}/appendChild", method = RequestMethod.POST)
    @ResponseBody
    public String create(Organization organization) {
        System.out.println("json: " + JsonUtils.toJson(organization));
        organizationService.createOrganization(organization);
        return ResponseUtils.successByDialogCloseCurrent("/organization", "/organization");
    }

    @RequiresPermissions("organization:update")
    @RequestMapping(value = "/{id}/maintain", method = RequestMethod.GET)
    public String showMaintainForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("organization", organizationService.findOne(id));
        return "organization/maintain";
    }

    @RequiresPermissions("organization:update")
    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    @ResponseBody
    public String update(Organization organization, RedirectAttributes redirectAttributes) {
//        organization.setParentIds(organization.makeSelfAsParentIds());
//        System.out.println("update: " + JsonUtils.toJson(organization));
//        organization.getParentIds();
        organizationService.updateOrganization(organization);
//        redirectAttributes.addFlashAttribute("msg", "修改成功");
//        return "redirect:/organization/success";
        return ResponseUtils.successByDialogCloseCurrent("/organization", "/organization");
    }

    @RequiresPermissions("organization:delete")
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
    @ResponseBody
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        organizationService.deleteOrganization(id);
//        redirectAttributes.addFlashAttribute("msg", "删除成功");
        return ResponseUtils.successByForwardUrl("/organization");
    }


    @RequiresPermissions("organization:update")
    @RequestMapping(value = "/{sourceId}/move", method = RequestMethod.GET)
    public String showMoveForm(@PathVariable("sourceId") Long sourceId, Model model) {
        Organization source = organizationService.findOne(sourceId);
        model.addAttribute("op", "移动");
        model.addAttribute("source", source);
        model.addAttribute("targetList", organizationService.findAllWithExclude(source));
        return "organization/dialogMove";
    }

    @RequiresPermissions("organization:update")
    @RequestMapping(value = "/{sourceId}/move", method = RequestMethod.POST)
    @ResponseBody
    public String move(
            @PathVariable("sourceId") Long sourceId,
            @RequestParam("targetId") Long targetId) {
        Organization source = organizationService.findOne(sourceId);
        Organization target = organizationService.findOne(targetId);
        organizationService.move(source, target);
//        System.out.println("resource: " + JsonUtils.toJson(source));
//        System.out.println("target: " + JsonUtils.toJson(target));
        return ResponseUtils.successByDialogCloseCurrent("/organization", "/organization");
    }

    @RequiresPermissions("organization:view")
    @RequestMapping(value = "/success", method = RequestMethod.GET)
    public String success() {
        return "organization/success";
    }

    /**
     * 修改
     * @param id
     * @param model
     * @return
     */
    @RequiresPermissions("organization:update")
    @RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
    public String update(@PathVariable("id") Long id, Model model) {
        model.addAttribute("organization", organizationService.findOne(id));
        return "organization/dialogEdit";
    }

}
