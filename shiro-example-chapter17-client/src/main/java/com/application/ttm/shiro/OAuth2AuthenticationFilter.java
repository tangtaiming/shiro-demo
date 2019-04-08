package com.application.ttm.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class OAuth2AuthenticationFilter extends AuthenticatingFilter {

    /**
     * oauth2 authc code 参数名
     */
    private String authcCodeParam = "code";

    /**
     * 客户端id
     */
    private String clientId;

    /**
     * 服务器登陆成功/失败重定向到客户端地址
     */
    private String redirectUrl;

    /**
     * oauth2服务器响应类型
     */
    private String responseType;

    private String failureUrl;

    public void setAuthcCodeParam(String authcCodeParam) {
        this.authcCodeParam = authcCodeParam;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }

    public void setFailureUrl(String failureUrl) {
        this.failureUrl = failureUrl;
    }

    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String code = httpServletRequest.getParameter(authcCodeParam);

        return new OAuth2Token(code);
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        String error = servletRequest.getParameter("error");
        String errorDes = servletRequest.getParameter("error_description");
        //如果服务端返回了错误
        if (!StringUtils.isEmpty(error)) {
            WebUtils.issueRedirect(servletRequest, servletResponse, failureUrl + "?error=" + error + "&error_description=" + errorDes);
            return false;
        }

        Subject subject = getSubject(servletRequest, servletResponse);
        if (!subject.isAuthenticated()) {
            if (StringUtils.isEmpty(servletRequest.getParameter(authcCodeParam))) {
                //如果用户没有身份验证，且没有auth code，则重定向到服务端授权
                saveRequestAndRedirectToLogin(servletRequest, servletResponse);
                return false;
            }
        }
        return executeLogin(servletRequest, servletResponse);
    }

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        issueSuccessRedirect(request, response);
        return false;
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        Subject subject = getSubject(request, response);
        if (subject.isAuthenticated() || subject.isRemembered()) {
            try {
                issueSuccessRedirect(request, response);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            try {
                WebUtils.issueRedirect(request, response, failureUrl);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        return false;
    }
}
