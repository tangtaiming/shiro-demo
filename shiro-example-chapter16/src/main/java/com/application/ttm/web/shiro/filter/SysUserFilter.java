package com.application.ttm.web.shiro.filter;

import com.application.ttm.Constant;
import com.application.ttm.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * <p>@Author tangtaiming</p>
 * <p>@Date 2018-12-27</p>
 * <p>@Version 1.0</p>
 **/
public class SysUserFilter extends PathMatchingFilter {

    @Autowired
    private UserService userService;

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) {
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        request.setAttribute(Constant.CURRENT_USER, userService.findByUsername(username));
        return true;
    }
}