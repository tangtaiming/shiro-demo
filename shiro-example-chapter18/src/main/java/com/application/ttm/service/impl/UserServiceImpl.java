package com.application.ttm.service.impl;

import com.application.ttm.JsonUtils;
import com.application.ttm.dao.UserDao;
import com.application.ttm.entity.Resource;
import com.application.ttm.entity.User;
import com.application.ttm.service.PasswordHelper;
import com.application.ttm.service.ResourceService;
import com.application.ttm.service.RoleService;
import com.application.ttm.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>@Author tangtaiming</p>
 * <p>@Date 2018-12-27</p>
 * <p>@Version 1.0</p>
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordHelper passwordHelper;

    @Autowired
    private RoleService roleService;

    @Autowired
    private ResourceService resourceService;

    @Override
    public User createUser(User user) {
        //加密密码
        passwordHelper.encryptPassword(user);
        System.out.println("---> " + JsonUtils.toJson(user));
        return userDao.createUser(user);
//        return null;
    }

    @Override
    public User updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public void deleteUser(Long userId) {
        userDao.deleteUser(userId);
    }

    @Override
    public void changePassword(Long userId, String newPassword) {
        User user = userDao.findOne(userId);
        user.setPassword(newPassword);
        passwordHelper.encryptPassword(user);
        System.out.println("user to dumper: " + JsonUtils.toJson(user));
        userDao.updateUser(user);
    }

    @Override
    public User findOne(Long userId) {
        return userDao.findOne(userId);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public Set<String> findRoles(String username) {
        User user = findByUsername(username);
        if (null == user) {
            return Collections.EMPTY_SET;
        }
        return roleService.findRoles(user.getRoleIds().toArray(new Long[0]));
    }

    @Override
    public Set<String> findPermissions(String username) {
        if ("admin".equals(username)) {
            Set<String> permissions = new HashSet<>();
            List<Resource> resources = resourceService.findAll();
            for (Resource row : resources) {
                String permimssion = row.getPermission();
//                System.out.println("row " + row.getType().getInfo());
//                System.out.println("resource type " + Resource.ResourceType.button.getInfo());
                if (row.isRootNode() || row.getType().getInfo().equals(Resource.ResourceType.button.getInfo())) {
                    continue;
                }
                permissions.add(permimssion);
            }
            return permissions;
        }
        User user = findByUsername(username);
        if (null == user) {
            return Collections.EMPTY_SET;
        }
        return roleService.findPermissions(user.getRoleIds().toArray(new Long[0]));
    }

}
