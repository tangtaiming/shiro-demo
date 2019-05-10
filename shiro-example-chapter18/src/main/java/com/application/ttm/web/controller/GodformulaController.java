package com.application.ttm.web.controller;

import com.application.ttm.entity.Godformula;
import com.application.ttm.service.GodformulaService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * <p>@Author tangtaiming</p>
 * <p>@Date 2019/5/10 23:47</p>
 * <p>@Version 1.0</p>
 */
@Controller
@RequestMapping("/godformula")
public class GodformulaController {

    @Autowired
    private GodformulaService godformulaService;

    @RequiresPermissions("godformula:view")
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
    public String execute(Model model) {
        List<Godformula> godformulas = godformulaService.findAll();
        model.addAttribute("godformulaList", godformulas);
        return "/godformula/list";
    }

}
