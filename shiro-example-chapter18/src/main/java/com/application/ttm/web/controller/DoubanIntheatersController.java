package com.application.ttm.web.controller;

import com.application.ttm.service.DoubanIntheatersService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/doubanIntheaters")
public class DoubanIntheatersController {

    @Autowired
    private DoubanIntheatersService doubanIntheatersService;

    @RequiresPermissions("doubanIntheaters:view")
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
    public String execute(Model model) {
        model.addAttribute("doubanIntheaters", doubanIntheatersService.findAll());
        return "doubanIntheaters/list";
    }

}
