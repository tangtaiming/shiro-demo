package com.application.ttm.shiro.service;

import com.application.ttm.shiro.entity.Authorize;

public interface AuthorizeService {

    /**
     * 授权
     * @param userId
     * @return
     */
    public Authorize findAuthorize(Long userId);

    /**
     * token查询
     * @param token
     * @return
     */
    public Authorize findAuthorizeByToken(String token);

}
