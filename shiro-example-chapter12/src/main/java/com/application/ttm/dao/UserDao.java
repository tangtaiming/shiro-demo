package com.application.ttm.dao;

import com.application.ttm.entity.User;

import java.util.Set;

/**
 * <p>@Author tangtaiming</p>
 * <p>@Date 2018-12-25</p>
 * <p>@Version 1.0</p>
 **/
public interface UserDao {

    public User createUser(User user);
    public void updateUser(User user);
    public void deleteUser(Long userId);

    public void correlationRoles(Long userId, Long... roleIds);
    public void uncorrelationRoles(Long userId, Long... roleIds);

    public User findOne(Long userId);

    public User findByUsername(String username);

    public Set<String> findRoles(String username);

    public Set<String> findPermissions(String username);
}
