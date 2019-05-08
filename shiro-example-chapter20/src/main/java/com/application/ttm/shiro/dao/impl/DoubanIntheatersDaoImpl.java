package com.application.ttm.shiro.dao.impl;

import com.application.ttm.shiro.dao.DoubanIntheatersDao;
import com.application.ttm.shiro.entity.DoubanIntheaters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>@Author tangtaiming</p>
 * <p>@Date 2019-05-08</p>
 * <p>@Version 1.0</p>
 **/
@Repository
public class DoubanIntheatersDaoImpl implements DoubanIntheatersDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public DoubanIntheaters findOne(Long id) {
        final String sql = "select id, title, small_image, douben_intheaters_id, year, stars, average from sys_douban_intheaters where id=?";

        List<DoubanIntheaters> doubanIntheaters = jdbcTemplate.query(sql, new BeanPropertyRowMapper(DoubanIntheaters.class), id);
        if(doubanIntheaters.size() == 0) {
            return null;
        }
        return doubanIntheaters.get(0);
    }

    @Override
    public List<DoubanIntheaters> findAll() {
        final String sql = "select id, title, small_image, douben_intheaters_id, year, stars, average from sys_douban_intheaters";
        List<DoubanIntheaters> doubanIntheaters = jdbcTemplate.query(sql, new BeanPropertyRowMapper(DoubanIntheaters.class));

        return doubanIntheaters;
    }
}
