package com.application.ttm.shiro.service.impl;

import com.application.ttm.shiro.dao.AuthorizeDao;
import com.application.ttm.shiro.entity.Authorize;
import com.application.ttm.shiro.service.AuthorizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorizeServiceImpl implements AuthorizeService {

    @Autowired
    private AuthorizeDao authorizeDao;

    /**
     * 授权
     *
     * @param userId
     * @return
     */
    @Override
    public Authorize findAuthorize(Long userId) {
        return authorizeDao.findOne(userId);
    }

    /**
     * token查询
     *
     * @param token
     * @return
     */
    @Override
    public Authorize findAuthorizeByToken(String token) {
        return authorizeDao.findAuthorizeByToken(token);
    }
}
