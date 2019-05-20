package com.application.ttm.dao.impl;

import com.application.ttm.dao.DoubanMovieDao;
import com.application.ttm.entity.DoubanMovie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class DoubanMovieDaoImpl implements DoubanMovieDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public DoubanMovie createDoubanMovie(DoubanMovie movie) {
        return null;
    }

    @Override
    public DoubanMovie updateDoubanMovie(DoubanMovie movie) {
        return null;
    }

    @Override
    public boolean deleteDoubanMovie(Long id) {
        return false;
    }

    @Override
    public List<DoubanMovie> findAll() {
        return null;
    }

    @Override
    public List<DoubanMovie> findList(Map<String, Object> param) {
        return null;
    }

    @Override
    public DoubanMovie findOne(Long id) {
        return null;
    }
}
