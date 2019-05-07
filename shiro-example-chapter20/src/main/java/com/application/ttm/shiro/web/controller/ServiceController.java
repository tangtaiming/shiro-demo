package com.application.ttm.shiro.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class ServiceController {

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String hello1(String[] param1, String param2) {
        System.out.println("------");
        return "hello" + param1[0] + param1[1] + param2;
    }
}