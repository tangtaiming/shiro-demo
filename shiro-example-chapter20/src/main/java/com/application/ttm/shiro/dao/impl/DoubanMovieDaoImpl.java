package com.application.ttm.shiro.dao.impl;

import com.application.ttm.shiro.dao.DoubanMovieDao;
import com.application.ttm.shiro.entity.DoubanMovie;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>@Author tangtaiming</p>
 * <p>@Date 2019/5/11 23:43</p>
 * <p>@Version 1.0</p>
 */
@Repository
public class DoubanMovieDaoImpl implements DoubanMovieDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<DoubanMovie> findAll() {
        final String sql = "select id, title, small_image, douben_intheaters_id, year, stars, average, original_title from sys_douban_movie";
        List<DoubanMovie> doubanMovieList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(DoubanMovie.class));

        return CollectionUtils.isEmpty(doubanMovieList) ? new ArrayList<>() : doubanMovieList;
    }

    @Override
    public List<DoubanMovie> findDoubanMovieByTop250() {
        final String sql = "select id, title, small_image, douben_intheaters_id, year, stars, average, original_title from sys_douban_movie where top250=?";
        List<DoubanMovie> doubanMovieList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(DoubanMovie.class), 1);

        return CollectionUtils.isEmpty(doubanMovieList) ? new ArrayList<>() : doubanMovieList;
    }

    @Override
    public List<DoubanMovie> findDoubanMovieByIntheaters() {
        final String sql = "select id, title, small_image, douben_intheaters_id, year, stars, average, original_title from sys_douban_movie where intheaters=?";
        List<DoubanMovie> doubanMovieList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(DoubanMovie.class), 1);

        return CollectionUtils.isEmpty(doubanMovieList) ? new ArrayList<>() : doubanMovieList;
    }
}
