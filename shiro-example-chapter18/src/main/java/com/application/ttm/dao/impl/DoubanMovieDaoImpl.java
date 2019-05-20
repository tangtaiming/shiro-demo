package com.application.ttm.dao.impl;

import com.application.ttm.dao.DoubanMovieDao;
import com.application.ttm.entity.DoubanMovie;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DoubanMovieDaoImpl implements DoubanMovieDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public DoubanMovie createDoubanMovie(DoubanMovie movie) {
        //TODO
        return null;
    }

    @Override
    public DoubanMovie updateDoubanMovie(DoubanMovie movie) {
        //TODO
        return null;
    }

    @Override
    public boolean deleteDoubanMovie(Long id) {
        //TODO
        return false;
    }

    @Override
    public List<DoubanMovie> findAll() {
        //TODO
        return null;
    }

    @Override
    public List<DoubanMovie> findList(int first, int pageSize) {
        final String sql = "select " +
                "id, title, small_image, douben_intheaters_id, " +
                "year, stars, average, create_date, creator, original_title, " +
                "summary, intheaters, top250 " +
                "from sys_douban_movie limit ?, ?";
        List<DoubanMovie> doubanMovies = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(DoubanMovie.class), first, pageSize);
        return CollectionUtils.isEmpty(doubanMovies) ? new ArrayList<>() : doubanMovies;
    }

    @Override
    public DoubanMovie findOne(Long id) {
        //TODO
        return null;
    }

    @Override
    public int count() {
        final String sql = "select count(*) from sys_douban_movie";
        int count = jdbcTemplate.queryForObject(sql, Integer.class);

        return count;
    }
}
