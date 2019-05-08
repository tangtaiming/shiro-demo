package com.application.ttm.shiro.web.controller;

import com.application.ttm.shiro.web.response.ResponseUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/hello")
public class ServiceController {

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> hello1(String[] param1, String param2) {
        return ResponseUtils.success();
    }
}