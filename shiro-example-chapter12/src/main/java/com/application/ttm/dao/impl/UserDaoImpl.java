package com.application.ttm.dao.impl;

import com.application.ttm.dao.UserDao;
import com.application.ttm.entity.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.PreparedStatement;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>@Author tangtaiming</p>
 * <p>@Date 2018-12-25</p>
 * <p>@Version 1.0</p>
 **/
public class UserDaoImpl extends JdbcDaoSupport implements UserDao {

    @Override
    public User createUser(User user) {
        final String sql = "insert into shiro_sys_users(username, password, salt, locked) values(?,?,?, ?)";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        getJdbcTemplate().update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getSalt());
            ps.setBoolean(4, user.getLocked());
            return ps;
        }, keyHolder);

        user.setId(keyHolder.getKey().longValue());
        return user;
    }

    @Override
    public void updateUser(User user) {
        String sql = "update shiro_sys_users set username=?, password=?, salt=?, locked=? where id=?";
        getJdbcTemplate().update(sql, user.getUsername(), user.getPassword(), user.getSalt(), user.getId());
    }

    @Override
    public void deleteUser(Long userId) {
        String sql = "delete from shiro_sys_users where id=?";
        getJdbcTemplate().update(sql, userId);
    }

    @Override
    public void correlationRoles(Long userId, Long... roleIds) {
        if(roleIds == null || roleIds.length == 0) {
            return;
        }
        String sql = "insert into shiro_sys_users_roles(user_id, role_id) values(?,?)";
        for(Long roleId : roleIds) {
            if(!exists(userId, roleId)) {
                getJdbcTemplate().update(sql, userId, roleId);
            }
        }
    }

    @Override
    public void uncorrelationRoles(Long userId, Long... roleIds) {
        if(roleIds == null || roleIds.length == 0) {
            return;
        }
        String sql = "delete from shiro_sys_users_roles where user_id=? and role_id=?";
        for(Long roleId : roleIds) {
            if(exists(userId, roleId)) {
                getJdbcTemplate().update(sql, userId, roleId);
            }
        }
    }

    private boolean exists(Long userId, Long roleId) {
        String sql = "select count(1) from shiro_sys_users_roles where user_id=? and role_id=?";
        return getJdbcTemplate().queryForObject(sql, Integer.class, userId, roleId) != 0;
    }


    @Override
    public User findOne(Long userId) {
        String sql = "select id, username, password, salt, locked from shiro_sys_users where id=?";
        List<User> userList = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(User.class), userId);
        if(userList.size() == 0) {
            return null;
        }
        return userList.get(0);
    }

    @Override
    public User findByUsername(String username) {
        String sql = "select id, username, password, salt, locked from shiro_sys_users where username=?";
        List<User> userList = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(User.class), username);
        if(userList.size() == 0) {
            return null;
        }
        return userList.get(0);
    }

    @Override
    public Set<String> findRoles(String username) {
        String sql = "select role from shiro_sys_users u, shiro_sys_roles r, shiro_sys_users_roles ur where u.username=? and u.id=ur.user_id and r.id=ur.role_id";
        return new HashSet(getJdbcTemplate().queryForList(sql, String.class, username));
    }

    @Override
    public Set<String> findPermissions(String username) {
        //TODO 此处可以优化，比如查询到role后，一起获取roleId，然后直接根据roleId获取即可
        String sql = "select permission from shiro_sys_users u, shiro_sys_roles r, shiro_sys_permissions p, shiro_sys_users_roles ur, shiro_sys_roles_permissions rp where u.username=? and u.id=ur.user_id and r.id=ur.role_id and r.id=rp.role_id and p.id=rp.permission_id";
        return new HashSet(getJdbcTemplate().queryForList(sql, String.class, username));
    }

}
