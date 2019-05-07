package com.application.ttm.shiro.realm;

import org.apache.shiro.authc.AuthenticationToken;

import java.util.Map;

public class StatelessToken implements AuthenticationToken {


    private String userid;
    private Map<String, ?> params;
    private String clientDigest;

    public StatelessToken(String userid, Map<String, ?> params, String clientDigest) {
        this.userid = userid;
        this.params = params;
        this.clientDigest = clientDigest;
    }

    @Override
    public Object getPrincipal() {
        return userid;
    }

    @Override
    public Object getCredentials() {
        return clientDigest;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Map<String, ?> getParams() {
        return params;
    }

    public void setParams(Map<String, ?> params) {
        this.params = params;
    }

    public String getClientDigest() {
        return clientDigest;
    }

    public void setClientDigest(String clientDigest) {
        this.clientDigest = clientDigest;
    }
}
