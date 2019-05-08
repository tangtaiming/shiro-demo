package com.application.ttm.shiro.web.controller;

import com.application.ttm.shiro.entity.DoubanIntheaters;
import com.application.ttm.shiro.service.DoubanIntheatersService;
import com.application.ttm.shiro.web.response.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * <p>@Author tangtaiming</p>
 * <p>@Date 2019-05-08</p>
 * <p>@Version 1.0</p>
 **/
@Controller
@RequestMapping("/doubanIntheaters")
public class DoubanIntheatersController {

    @Autowired
    private DoubanIntheatersService doubanIntheatersService;

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> findAll() {
        List<DoubanIntheaters> doubanIntheatersList = doubanIntheatersService.findAll();
        return ResponseUtils.success(doubanIntheatersList);
    }

}
