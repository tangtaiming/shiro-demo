package com.application.ttm.shiro.web.vo;

import com.application.ttm.shiro.Constants;
import org.apache.oltu.oauth2.common.error.OAuthError;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import java.nio.charset.Charset;

/**
 * 返回请求
 */
public class ResponseVo {

    private ResponseEntity entity;

    private HttpHeaders headers;

    public ResponseVo() {
        MediaType mediaType = new MediaType("text","html",Charset.forName("utf-8"));
        headers = new HttpHeaders();
        headers.setContentType(mediaType);
    }

    /**
     * 错误请求
     * @param code
     * @param error
     * @param des
     * @return
     * @throws OAuthSystemException
     */
    public ResponseEntity error(int code, String error, String des) throws OAuthSystemException {
        OAuthResponse response =
                OAuthResponse.errorResponse(code)
                        .setState(String.valueOf(code))
                        .setError(error)
                        .setErrorDescription(des)
                        .buildJSONMessage();
        entity = new ResponseEntity(response.getBody(), headers, HttpStatus.valueOf(response.getResponseStatus()));
        return entity;
    }

}
