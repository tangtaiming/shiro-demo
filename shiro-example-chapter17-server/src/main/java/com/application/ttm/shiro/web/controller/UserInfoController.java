package com.application.ttm.shiro.web.controller;

import com.application.ttm.shiro.Constants;
import com.application.ttm.shiro.service.OAuthService;
import com.application.ttm.shiro.web.vo.ResponseVo;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.error.OAuthError;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.oltu.oauth2.common.message.types.ParameterStyle;
import org.apache.oltu.oauth2.common.utils.OAuthUtils;
import org.apache.oltu.oauth2.rs.request.OAuthAccessResourceRequest;
import org.apache.oltu.oauth2.rs.response.OAuthRSResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class UserInfoController {

    @Autowired
    private OAuthService oAuthService;

    @RequestMapping("/userInfo")
    public HttpEntity userInfo(HttpServletRequest request) throws OAuthSystemException {
        try {
            //构建OAuth资源请求
            OAuthAccessResourceRequest oauthRequest = new OAuthAccessResourceRequest(request, ParameterStyle.QUERY);

            //获取Access Token
            String accountToken = oauthRequest.getAccessToken();

            //验证Access Token
            if (!oAuthService.checkAccessToken(accountToken)) {
                // 如果不存在/过期了，返回未验证错误，需重新验证
//                OAuthResponse oAuthResponse = OAuthRSResponse
//                        .errorResponse(HttpServletResponse.SC_UNAUTHORIZED)
//                        .setRealm(Constants.RESOURCE_SERVER_NAME)
//                        .setError(OAuthError.ResourceResponse.INVALID_TOKEN)
//                        .buildJSONMessage();
                ResponseVo responseVo = new ResponseVo();
                return responseVo.error(
                        HttpServletResponse.SC_UNAUTHORIZED,
                        OAuthError.ResourceResponse.INVALID_TOKEN,
                        "token 未授权");
            }

            //返回用户名
            String username = oAuthService.getUsernameByAccessToken(accountToken);
            return new ResponseEntity(username, HttpStatus.OK);
        } catch (OAuthProblemException e) {
            //检查是否设置了错误码
            String errorCode = e.getError();
            if (OAuthUtils.isEmpty(errorCode)) {
                ResponseVo responseVo = new ResponseVo();
                return responseVo.error(
                        HttpServletResponse.SC_UNAUTHORIZED,
                        Constants.RESOURCE_SERVER_NAME,
                        Constants.RESOURCE_SERVER_NAME);
            }

//            OAuthResponse oauthResponse = OAuthRSResponse
//                    .errorResponse(HttpServletResponse.SC_UNAUTHORIZED)
//                    .setRealm(Constants.RESOURCE_SERVER_NAME)
//                    .setError(e.getError())
//                    .setErrorDescription(e.getDescription())
//                    .setErrorUri(e.getUri())
//                    .buildHeaderMessage();

            ResponseVo responseVo = new ResponseVo();
            return responseVo.error(
                    HttpServletResponse.SC_UNAUTHORIZED,
                    e.getError(),
                    e.getDescription());
        }
    }

}
