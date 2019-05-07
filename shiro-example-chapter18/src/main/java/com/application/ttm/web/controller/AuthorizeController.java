package com.application.ttm.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/authorize")
public class AuthorizeController {

    @RequestMapping(method = {RequestMethod.POST})
    @ResponseBody
    public String authorize() {
        return "";
    }

}
