package com.application.ttm.service.impl;

import com.application.ttm.JsonUtils;
import com.application.ttm.dao.AuthorizeDao;
import com.application.ttm.entity.Authorize;
import com.application.ttm.service.AuthorizeService;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorizeServiceImpl implements AuthorizeService {

    @Autowired
    private AuthorizeDao authorizeDao;

    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

    /**
     * 授权
     *
     * @param authorize
     * @return
     */
    @Override
    public Authorize authorize(Authorize authorize) {
        authorize.setToken(randomNumberGenerator.nextBytes().toHex());
        System.out.println("authorize: " + JsonUtils.toJson(authorize));
        return authorizeDao.createAuthorize(authorize);
    }

}
