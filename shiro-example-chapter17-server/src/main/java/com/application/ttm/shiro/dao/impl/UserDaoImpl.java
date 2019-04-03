package com.application.ttm.shiro.dao.impl;

import com.application.ttm.shiro.dao.UserDao;
import com.application.ttm.shiro.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

/**
 * <p>@Author tangtaiming</p>
 * <p>@Date 2019-04-03</p>
 * <p>@Version 1.0</p>
 **/
@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public User createUser(User user) {
        final String sql = "insert into oauth2_user(username, password, salt) values(?,?,?)";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getSalt());
            return ps;
        }, keyHolder);

        user.setId(keyHolder.getKey().longValue());
        return user;
    }

    @Override
    public User updateUser(User user) {
        final String sql = "update oauth2_user set username=?, password=?, salt=? where id=?";

        jdbcTemplate.update(
                sql,
                user.getUsername(), user.getPassword(), user.getSalt(), user.getId());
        return user;
    }

    @Override
    public void deleteUser(Long userId) {
        final String sql = "delete from oauth2_user where id=?";

        jdbcTemplate.update(sql, userId);
    }

    @Override
    public User findOne(Long userId) {
        String sql = "select id, username, password, salt from oauth2_user where id=?";
        List<User> userList = jdbcTemplate.query(sql, new BeanPropertyRowMapper(User.class), userId);
        if(userList.size() == 0) {
            return null;
        }
        return userList.get(0);
    }

    @Override
    public List<User> findAll() {
        String sql = "select id, username, password, salt from oauth2_user";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper(User.class));
    }

    @Override
    public User findByUsername(String username) {
        String sql = "select id, username, password, salt from oauth2_user where username=?";
        List<User> userList = jdbcTemplate.query(sql, new BeanPropertyRowMapper(User.class), username);
        if(userList.size() == 0) {
            return null;
        }
        return userList.get(0);
    }

}
