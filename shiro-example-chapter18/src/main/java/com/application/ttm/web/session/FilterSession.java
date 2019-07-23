package com.application.ttm.web.session;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * <p>@Author tangtaiming</p>
 * <p>@Date 2019-07-23</p>
 * <p>@Version 1.0</p>
 **/
public class FilterSession {

    private String ormClass;

    public FilterSession(String ormClass) {}

    public FilterSession(ServletRequestAttributes requestAttributes, String ormClass) {
//        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest servletRequest = requestAttributes.getRequest();
        String requestUri = servletRequest.getRequestURI();
        Enumeration params = servletRequest.getParameterNames();
        while (params.hasMoreElements()) {
            String key = (String) params.nextElement();
            String value = servletRequest.getParameter(key);
        }
    }

    /**
     * 比较
     * @param param
     * @return
     */
    private List<String> parseSimpleExpressionCompare(String param) {
        String compare = "eq";
        if (param.endsWith("[from]")) {
            param = param.substring(0, param.indexOf("[from]"));
            compare = "ge";
        } else if (param.endsWith("[to]")) {
            param = param.substring(0, param.indexOf("[to]"));
            compare = "le";
        } else if (param.endsWith("[like]")) {
            param = param.substring(0, param.indexOf("[like]"));
            compare = "like";
        } else if (param.endsWith("[null]")) {
            param = param.substring(0, param.indexOf("[null]"));
            compare = "null";
        } else if (param.endsWith("[!null]")) {
            param = param.substring(0, param.indexOf("[!null]"));
            compare = "!null";
        } else if (param.endsWith("[true]")) {
            param = param.substring(0, param.indexOf("[true]"));
            compare = "true";
        } else if (param.endsWith("[!true]")) {
            param = param.substring(0, param.indexOf("[!true]"));
            compare = "!true";
        }

        List<String> result = new ArrayList<String>();
        result.add(param);
        result.add(compare);
        return result;
    }

    /**
     * 类型
     * @param param
     * @return
     * @throws ClassNotFoundException
     * @throws NoSuchFieldException
     */
    private List<String> parseSimpleExpressionCast(String param) throws ClassNotFoundException, NoSuchFieldException {
        List<String> result = new ArrayList<String>();
        String cast;
        if (param.endsWith("[int]")) {
            param = param.substring(0, param.indexOf("[int]"));
            cast = "int";
        } else if (param.endsWith("[long]")) {
            param = param.substring(0, param.indexOf("[long]"));
            cast = "long";
        } else if (param.endsWith("[float]")) {
            param = param.substring(0, param.indexOf("[float]"));
            cast = "float";
        } else if (param.endsWith("[double]")) {
            param = param.substring(0, param.indexOf("[double]"));
            cast = "double";
        } else if (param.endsWith("[string]")) {
            param = param.substring(0, param.indexOf("[string]"));
            cast = "string";
        } else {
            String ormClass = getOrmClass();
            if (null == ormClass) {
                cast = "string";
            } else {
                cast = Class.forName(ormClass).getDeclaredField(param).getType().getName();
            }
        }
        result.add(param);
        result.add(cast);

        return result;
    }

    public String getOrmClass() {
        return ormClass;
    }

    public void setOrmClass(String ormClass) {
        this.ormClass = ormClass;
    }
}
