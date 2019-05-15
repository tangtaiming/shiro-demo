package com.application.ttm.web.spring.filter;

import org.springframework.web.filter.RequestContextFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>@Author tangtaiming</p>
 * <p>@Date 2019/5/15 23:31</p>
 * <p>@Version 1.0</p>
 */
public class MyRequestFilter extends RequestContextFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        super.doFilterInternal(request, response, filterChain);
        System.out.println("method: " + request.getMethod() + " request url: " + request.getRequestURI());
    }
}



