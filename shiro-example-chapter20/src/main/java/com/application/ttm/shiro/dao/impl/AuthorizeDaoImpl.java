package com.application.ttm.shiro.dao.impl;

import com.application.ttm.shiro.dao.AuthorizeDao;
import com.application.ttm.shiro.entity.Authorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class AuthorizeDaoImpl implements AuthorizeDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Authorize createAuthorize(Authorize authorize) {
        final String sql = "insert into sys_authorize(token, create_date, user_id) values(?,?,?)";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, authorize.getToken());
            ps.setString(2, authorize.getCreateDate());
            ps.setLong(3, authorize.getUserId());
            return ps;
        }, keyHolder);

        authorize.setId(keyHolder.getKey().longValue());

        return authorize;
    }

    @Override
    public Authorize updateAuthorize(Authorize authorize) {
        final String sql = "update sys_authorize set token=?, create_date=?, user_id=? where id=?";
        jdbcTemplate.update(sql, authorize.getToken(), authorize.getCreateDate(), authorize.getUserId(), authorize.getId());
        return authorize;
    }

    @Override
    public void deleteAuthorize(Long id) {
        final String sql = "delete from sys_authorize where id=?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public Authorize findOne(Long id) {
        final String sql = "select id, token, create_date, user_id from sys_authorize where id=?";
        List<Authorize> authorizes = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Authorize.class), id);
        if (null == authorizes) {
            return null;
        }
        return authorizes.get(0);
    }

    @Override
    public List<Authorize> findAll() {
        final String sql = "select id, token, create_date, user_id from sys_authorize";
        List<Authorize> authorizes = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Authorize.class));
        return authorizes;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}