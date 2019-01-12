package com.application.ttm.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 * <p>@Author tangtaiming</p>
 * <p>@Date 2019-01-12</p>
 * <p>@Version 1.0</p>
 **/
public class MyRealm2 implements Realm {

    @Override
    public String getName() {
        return "MyRealm2";
    }

    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
        return authenticationToken instanceof UsernamePasswordToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        String password = new String((char[]) authenticationToken.getCredentials());
        if (!"deng".equals(username)) {
            throw new UnknownAccountException();    //账号错误
        }
        if (!"123".equals(password)) {
            throw new IncorrectCredentialsException();  //密码错误
        }

        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(username, password, getName());
        return simpleAuthenticationInfo;
    }
}
