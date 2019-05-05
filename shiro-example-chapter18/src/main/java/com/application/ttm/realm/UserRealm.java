package com.application.ttm.realm;

import com.application.ttm.entity.Resource;
import com.application.ttm.entity.User;
import com.application.ttm.service.ResourceService;
import com.application.ttm.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>@Author tangtaiming</p>
 * <p>@Date 2018-12-27</p>
 * <p>@Version 1.0</p>
 **/
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private ResourceService resourceService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) principalCollection.getPrimaryPrincipal();

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        if (username.equals("admin")) {
            List<Resource> resourceList = resourceService.findAll();
            Set<String> permissionList = new HashSet<>();
            for (Resource row : resourceList) {
                //如果是root 菜单或者 按钮顾虑
                if (row.isRootNode() || row.getType().getInfo().equals(Resource.ResourceType.button.getInfo())) {
                    continue;
                }
                permissionList.add(row.getPermission());
            }
//            authorizationInfo.setRoles(userService.findRoles(username));
            authorizationInfo.setStringPermissions(permissionList);
        } else {
            authorizationInfo.setRoles(userService.findRoles(username));
            authorizationInfo.setStringPermissions(userService.findPermissions(username));
        }

        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();

        User user = userService.findByUsername(username);
        //没有找到账号
        if (null == user) {
            throw new UnknownAccountException();
        }

        //账号锁定
        if (Boolean.TRUE.equals(user.getLocked())) {
            throw new LockedAccountException();
        }

        System.out.println("username: " + user.getUsername());
        System.out.println("password: " + user.getPassword());
        System.out.println("credentialsSalt: " + ByteSource.Util.bytes(user.getCredentialsSalt()));

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user.getUsername(),
                user.getPassword(),
                ByteSource.Util.bytes(user.getCredentialsSalt()),
                getName()
        );
        return authenticationInfo;
    }

    @Override
    protected void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    protected void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    @Override
    protected void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }

}
