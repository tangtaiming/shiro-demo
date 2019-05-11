package com.application.ttm.shiro.service.impl;

import com.application.ttm.shiro.dao.DoubanMovieDao;
import com.application.ttm.shiro.entity.DoubanMovie;
import com.application.ttm.shiro.service.DoubanMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>@Author tangtaiming</p>
 * <p>@Date 2019/5/11 23:56</p>
 * <p>@Version 1.0</p>
 */
@Service
public class DoubanMovieServiceImpl implements DoubanMovieService {

    @Autowired
    private DoubanMovieDao doubanMovieDao;

    @Override
    public List<DoubanMovie> findAll() {
        return doubanMovieDao.findAll();
    }

    @Override
    public List<DoubanMovie> findDoubanMovieByTop250() {
        return doubanMovieDao.findDoubanMovieByTop250();
    }

    @Override
    public List<DoubanMovie> findDoubanMovieByIntheaters() {
        return doubanMovieDao.findDoubanMovieByIntheaters();
    }
}
