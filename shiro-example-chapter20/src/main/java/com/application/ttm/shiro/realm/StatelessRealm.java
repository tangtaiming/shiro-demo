package com.application.ttm.shiro.realm;

import com.application.ttm.shiro.codec.HmacSHA256Utils;
import com.application.ttm.shiro.entity.Authorize;
import com.application.ttm.shiro.service.AuthorizeService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class StatelessRealm extends AuthorizingRealm {

    @Autowired
    private AuthorizeService authorizeService;

    @Override
    public boolean supports(AuthenticationToken token) {
        //仅支持StatelessToken类型的Token
        return token instanceof StatelessToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //根据用户名查找角色，请根据需求实现
//        String username = (String) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        authorizationInfo.addRole(username);
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        StatelessToken token = (StatelessToken) authenticationToken;
//        String username = ((StatelessToken) authenticationToken).getUserid();

        Authorize authorize = queryToken(token.getClientDigest());
        //根据用户名获取token（和客户端的一样）
//        String serverDigest = HmacSHA256Utils.digest(key, token.getParams());
        System.out.println(token.getClientDigest());
        System.out.println(authorize.getToken());
        //然后进行客户端消息摘要和服务器端消息摘要的匹配
        return new SimpleAuthenticationInfo(authorize.toString(), authorize.getToken(), getName());
    }

    private String getKey(String userId) {
        Long cuserId = Long.valueOf(userId);
        Authorize authorize = authorizeService.findAuthorize(cuserId);
        if (!(null == authorize) && authorize.getUserId().equals(cuserId)) {
            return authorize.getToken();
        }
        return null;
    }

    private Authorize queryToken(String token) {
        Authorize authorize = authorizeService.findAuthorizeByToken(token);
        if (!(null == authorize)) {
            return authorize;
        }
        throw new IllegalArgumentException("Authorize query null, fail!");
    }

    public void setAuthorizeService(AuthorizeService authorizeService) {
        this.authorizeService = authorizeService;
    }
}
