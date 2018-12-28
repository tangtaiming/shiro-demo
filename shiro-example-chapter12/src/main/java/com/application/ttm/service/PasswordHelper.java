package com.application.ttm.service;

import com.application.ttm.entity.User;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * <p>@Author tangtaiming</p>
 * <p>@Date 2018-12-26</p>
 * <p>@Version 1.0</p>
 **/
public class PasswordHelper {

    private RandomNumberGenerator generator = new SecureRandomNumberGenerator();
    private String algorithmName = "md5";
    private int hashIterations = 2;

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public void setHashIterations(int hashIterations) {
        this.hashIterations = hashIterations;
    }

    public void encryptPassword(User user) {
        user.setSalt(generator.nextBytes().toHex());

        String newPassword = new SimpleHash(
            algorithmName,
            user.getPassword(),
            ByteSource.Util.bytes(user.getCredentialsSalt()),
            hashIterations)
                .toHex();

        user.setPassword(newPassword);
    }

}
