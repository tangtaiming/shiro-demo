package com.application.ttm.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 * <p>@Author tangtaiming</p>
 * <p>@Date 2019-01-12</p>
 * <p>@Version 1.0</p>
 **/
public class MyRealm1 implements Realm {

    @Override
    public String getName() {
        return "MyRealm1";
    }

    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
        return authenticationToken instanceof UsernamePasswordToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        String password = new String((char[]) authenticationToken.getCredentials());
        if (!"tang".equals(username)) {
            throw new UnknownAccountException();
        }
        if (!"123".equals(password)) {
            throw new IncorrectCredentialsException();
        }

        return new SimpleAuthenticationInfo(username, password, getName());
    }
}
