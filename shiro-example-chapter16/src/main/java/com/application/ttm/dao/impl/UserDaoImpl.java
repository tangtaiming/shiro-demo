package com.application.ttm.dao.impl;

import com.application.ttm.dao.UserDao;
import com.application.ttm.entity.User;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

/**
 * <p>@Author tangtaiming</p>
 * <p>@Date 2018-12-27</p>
 * <p>@Version 1.0</p>
 **/
@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public User createUser(User user) {
        final String sql = "insert into sys_user(organization_id, username, password, salt, role_ids, locked) values(?, ?, ?, ?, ?, ?)";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setLong(1, -1L);
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getSalt());
            ps.setString(5, user.getRoleIdsStr());
            ps.setBoolean(6, user.getLocked());
            return ps;
        }, keyHolder);

        user.setId(keyHolder.getKey().longValue());
        return user;
    }

    @Override
    public User updateUser(User user) {
        final String sql = "update sys_user set organization_id=?,username=?, password=?, salt=?, role_ids=?, locked=? where id=?";

        jdbcTemplate.update(sql, user.getOrganizationId(), user.getUsername(), user.getPassword(), user.getSalt(), user.getRoleIdsStr(), user.getLocked(), user.getId());
        return user;
    }

    @Override
    public void deleteUser(Long userId) {
        final String sql = "delete from sys_user where id=?";

        jdbcTemplate.update(sql, userId);
    }

    @Override
    public User findOne(Long userId) {
        final String sql = "select id, organization_id, username, password, salt, role_ids as roleIdsStr, locked from sys_user where id=?";

        List<User> userList = jdbcTemplate.query(sql, new BeanPropertyRowMapper(User.class), userId);
        return CollectionUtils.isEmpty(userList) ? null : userList.get(0);
    }

    @Override
    public List<User> findAll() {
        final String sql = "select id, organization_id, username, password, salt, role_ids as roleIdsStr, locked from sys_user";

        List<User> userList = jdbcTemplate.query(sql, new BeanPropertyRowMapper(User.class));
        return CollectionUtils.isEmpty(userList) ? null : userList;
    }

    @Override
    public User findByUsername(String username) {
        final String sql = "select id, organization_id, username, password, salt, role_ids as roleIdsStr, locked from sys_user where username=?";

        List<User> userList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), username);
        return CollectionUtils.isEmpty(userList) ? null : userList.get(0);
    }

}
