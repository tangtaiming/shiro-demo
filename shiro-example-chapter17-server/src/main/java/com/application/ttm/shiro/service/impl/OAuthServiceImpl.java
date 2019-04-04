package com.application.ttm.shiro.service.impl;

import com.application.ttm.shiro.service.ClientService;
import com.application.ttm.shiro.service.OAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

/**
 * <p>@Author tangtaiming</p>
 * <p>@Date 2019-04-04</p>
 * <p>@Version 1.0</p>
 **/
@Service
public class OAuthServiceImpl implements OAuthService {

    private Cache cache;

    @Autowired
    private ClientService clientService;

    @Autowired
    public OAuthServiceImpl(CacheManager cacheManager) {
        cache = cacheManager.getCache("code-cache");
    }

    @Override
    public void addAuthCode(String authCode, String username) {
        cache.put(authCode, username);
    }

    @Override
    public void addAccessToken(String accessToken, String username) {
        cache.put(accessToken, username);
    }

    @Override
    public boolean checkAuthCode(String authCode) {
        return !(null == cache.get(authCode));
    }

    @Override
    public boolean checkAccessToken(String accessToken) {
        return !(null == cache.get(accessToken));
    }

    @Override
    public String getUsernameByAuthCode(String authCode) {
        return (String) cache.get(authCode).get();
    }

    @Override
    public String getUsernameByAccessToken(String accessToken) {
        return (String) cache.get(accessToken).get();
    }

    @Override
    public long getExpireIn() {
        return 3600L;
    }

    @Override
    public boolean checkClientId(String clientId) {
        return !(null == clientService.findByClientId(clientId));
    }

    @Override
    public boolean checkClientSecret(String clientSecret) {
        return !(null == clientService.findByClientSecret(clientSecret));
    }

}
