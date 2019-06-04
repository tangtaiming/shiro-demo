package com.application.ttm.entity;

/**
 * JOYBUY api配置
 * <p>@Author tangtaiming</p>
 * <p>@Date 2019-06-03</p>
 * <p>@Version 1.0</p>
 **/
public class JoybuyApiConfig {

    private Integer id;

    /**
     * 授权类型，固定值
     */
    private String grantType;

    /**
     * 应用Key  创建应用时由平台分配
     */
    private String appKey;

    /**
     * 应用秘钥 创建应用时由平台分配
     */
    private String appSecret;

    private String token;

    private String refreshToken;

}
