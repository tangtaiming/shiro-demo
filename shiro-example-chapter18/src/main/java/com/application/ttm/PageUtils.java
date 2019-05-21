package com.application.ttm;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class PageUtils {

    public static int getPageNum(Map<String, Object> param) {
        HttpServletRequest request = SpringUtils.getRequest();
        String requestUrl = request.getRequestURI();
        String userAdmin = (String) SecurityUtils.getSubject().getPrincipal();
        HttpSession session = SpringUtils.getSession();
        String key = userAdmin + requestUrl + "-pageNum";
        String pageNum = (String) param.get("pageNum");
        if (StringUtils.isEmpty(pageNum)) {
            pageNum = (String) session.getAttribute(key);
            if (StringUtils.isEmpty(pageNum)) {
                pageNum = "1";
            }
        }
        session.setAttribute(key, pageNum);
        return Integer.valueOf(pageNum);
    }

    public static Integer getNumPerPage(Map<String, Object> param) {
        HttpServletRequest request = SpringUtils.getRequest();
        String requestUrl = request.getRequestURI();
        String userAdmin = (String) SecurityUtils.getSubject().getPrincipal();
        HttpSession session = SpringUtils.getSession();
        String key = userAdmin + requestUrl + "-numPerPage";
        String numPerPage = (String) param.get("numPerPage");
        if (StringUtils.isEmpty(numPerPage)) {
            numPerPage = (String) session.getAttribute(key);
            if (StringUtils.isEmpty(numPerPage)) {
                numPerPage = "20";
            }
        }
        session.setAttribute(key, numPerPage);
        return Integer.valueOf(numPerPage);
    }

}
