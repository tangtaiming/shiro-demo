package com.application.ttm.web.spring.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * <p>@Author tangtaiming</p>
 * <p>@Date 2019/5/15 23:53</p>
 * <p>@Version 1.0</p>
 */
public class MyRequestInterceptor implements HandlerInterceptor {

    private final static String PAGE_NUMBER = "PAGENUMBER";

    private final static  String PAGE_SIZE = "PAGESIZE";

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
//        System.out.println("request url: " + httpServletRequest.getRequestURI() + " method: " + httpServletRequest.getMethod());
//        Map<String, String[]> params = httpServletRequest.getParameterMap();
//        params.get(PAGE_NUMBER);
//        params.get(PAGE_SIZE);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

}
