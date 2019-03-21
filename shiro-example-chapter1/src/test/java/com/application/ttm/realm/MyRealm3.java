package com.application.ttm.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 * <p>User: tangtaiming
 * <p>Date: 2019-01-13
 * <p>Version: 1.0
 */
public class MyRealm3 implements Realm {

    @Override
    public String getName() {
        return "MyRealm3";
    }

    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
        return authenticationToken instanceof UsernamePasswordToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        String password = new String((char[]) authenticationToken.getCredentials());
        if (!"xiaoming".equals(username)) {
            throw new UnknownAccountException();
        }
        if (!"123".equals(password)) {
            throw new IncorrectCredentialsException();  //密码错误
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, password, getName());
        return info;
    }
}
