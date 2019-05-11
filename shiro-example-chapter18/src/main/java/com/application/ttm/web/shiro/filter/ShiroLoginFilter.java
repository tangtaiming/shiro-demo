package com.application.ttm.web.shiro.filter;

import com.application.ttm.ResponseUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>@Author tangtaiming</p>
 * <p>@Date 2019/5/11 11:24</p>
 * <p>@Version 1.0</p>
 */
public class ShiroLoginFilter extends FormAuthenticationFilter {

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        if (isAjax(request)) {
            httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse.setContentType("application/json");
//            ResultData resultData = new ResultData();
//            resultData.setResult(1);
//            resultData.setCode(403);
//            resultData.setMessage("登录认证失效，请重新登录!");
//            httpServletResponse.getWriter().write(JSONObject.toJSON(resultData).toString());
            httpServletResponse.getWriter().write(ResponseUtils.loginFail());
        } else {
            /**
             * @Mark 非ajax请求重定向为登录页面
             */
            httpServletResponse.sendRedirect("/login");
        }
        return false;
    }

    private boolean isAjax(ServletRequest request){
        String header = ((HttpServletRequest) request).getHeader("X-Requested-With");
        if("XMLHttpRequest".equalsIgnoreCase(header)){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

}
