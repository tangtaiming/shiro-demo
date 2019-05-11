package com.application.ttm.shiro.web.controller;

import com.application.ttm.shiro.entity.DoubanMovie;
import com.application.ttm.shiro.service.DoubanMovieService;
import com.application.ttm.shiro.web.response.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * <p>@Author tangtaiming</p>
 * <p>@Date 2019/5/11 23:58</p>
 * <p>@Version 1.0</p>
 */
@RestController
@RequestMapping("/doubanMovie")
public class DoubanMovieController {

    @Autowired
    private DoubanMovieService doubanMovieService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> doubanMovie() {
        List<DoubanMovie> doubanMovieList = doubanMovieService.findAll();
        return ResponseUtils.success(doubanMovieList);
    }

    @RequestMapping(value = "/top250", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> top250() {
        List<DoubanMovie> doubanMovieList = doubanMovieService.findDoubanMovieByTop250();
        return ResponseUtils.success(doubanMovieList);
    }

    @RequestMapping(value = "/intheaters", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> intheaters() {
        List<DoubanMovie> doubanMovieList = doubanMovieService.findDoubanMovieByIntheaters();
        return ResponseUtils.success(doubanMovieList);
    }

}
