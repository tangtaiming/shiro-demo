package com.application.ttm.web.controller;

import com.application.ttm.service.DoubanMovieService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("/doubanMovie")
public class DoubanMovieController {

    @Autowired
    private DoubanMovieService doubanMovieService;

    @RequiresPermissions("doubanMovie:view")
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
    public String execute(Model model, @RequestParam Map<String, Object> param) {
        model.addAttribute("doubanMovies", doubanMovieService.findList(param));
        model.addAttribute("pageNum", doubanMovieService.getPageNum(param));
        model.addAttribute("numPerPage", doubanMovieService.getNumPerPage(param));
        model.addAttribute("totalCount", doubanMovieService.count());
        return "doubanMovie/list";
    }

}
