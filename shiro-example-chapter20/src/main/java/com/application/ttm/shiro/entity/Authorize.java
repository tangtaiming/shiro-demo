package com.application.ttm.shiro.entity;

/**
 * 授权
 */
public class Authorize {

    private Long id;    //编号
    private String token;   //token
    private String createDate;  //创建时间
    private Long userId;    //用户ID

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
