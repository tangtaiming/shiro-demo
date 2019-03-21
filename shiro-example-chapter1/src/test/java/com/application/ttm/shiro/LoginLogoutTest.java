package com.application.ttm.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

/**
 * <p>@Author tangtaiming</p>
 * <p>@Date 2019-01-12</p>
 * <p>@Version 1.0</p>
 **/
public class LoginLogoutTest {

    @Test
    public void testUsersLogin() {
        //1.获取 SecurityManager 工厂，此处使用 Ini 配置文件初始化 SecurityManager
        Factory factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        //2.得到 SecurityManager 实例 并绑定给 SecurityUtils
        SecurityManager manager = (SecurityManager) factory.getInstance();
        SecurityUtils.setSecurityManager(manager);
        //3.得到 Subject 及创建用户名/密码身份验证 Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("tang", "123");
        //5.登录，即身份验证
        try {
            subject.login(token);
        } catch (Exception e) {
            //验证失败
        }

        //断言用户已经登录
        try {
            Assert.assertEquals(true, subject.isAuthenticated());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //6.退出
        subject.logout();
    }

    @Test
    public void testCustomRealm() {
        //1.获取 SecurityManager 工厂
        Factory factory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");
        //2.获得 SecurityManager 实例 并绑定到 SecurityUtils
        SecurityManager manager = (SecurityManager) factory.getInstance();
        SecurityUtils.setSecurityManager(manager);
        //3.得到 Subject 及创建用户名/密码身份验证 Token
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("tang", "123");
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            //to do
        }

        //断言
        Assert.assertEquals(true, subject.isAuthenticated());

        //退出登录
        subject.logout();
    }

    @Test
    public void testCustomMutilRealm() {
        //1.获取 SecurityManager 工厂
        Factory factory = new IniSecurityManagerFactory("classpath:shiro-mutil-realm.ini");
        //2.获取 SecurityManager 实体类
        SecurityManager manager = (SecurityManager) factory.getInstance();
        SecurityUtils.setSecurityManager(manager);
        //3.获取 Subject 对象
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("tang", "123");

        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }

        //断言 成功
        Assert.assertEquals(true, subject.isAuthenticated());

        //退出
        subject.logout();
    }

    @Test
    public void testCustomJdbcRealm() {
        //1.获取 SecurityManager 工厂
        Factory factory = new IniSecurityManagerFactory("classpath:shiro-jdbc.ini");
        //2.获取 SecurityManager 实例
        SecurityManager manager = (SecurityManager) factory.getInstance();
        SecurityUtils.setSecurityManager(manager);
        //3.获取 Subject 实例 并且获取token
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("tang", "123");

        //4.进行登录
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            //登录失败
            e.printStackTrace();
        }

        //断言 登录成功
        Assert.assertEquals(true, subject.isAuthenticated());

        //5.退出登录
        subject.logout();
    }

}
