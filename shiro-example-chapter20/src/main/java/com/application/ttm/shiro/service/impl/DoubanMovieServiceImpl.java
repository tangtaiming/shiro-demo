package com.application.ttm.shiro.service.impl;

import com.application.ttm.shiro.converter.DoubanMovieIntheatersRsConverter;
import com.application.ttm.shiro.converter.DoubanMovieTop250RsConverter;
import com.application.ttm.shiro.dao.DoubanMovieDao;
import com.application.ttm.shiro.entity.DoubanMovie;
import com.application.ttm.shiro.service.DoubanMovieService;
import com.application.ttm.shiro.web.vo.DoubanMovieIntheatersRsVo;
import com.application.ttm.shiro.web.vo.DoubanMovieTop250RsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
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
    public List<DoubanMovieTop250RsVo> findDoubanMovieByTop250(int start, int count) {
        List<DoubanMovie> doubanMovieList = doubanMovieDao.findDoubanMovieByTop250Page(start, count);
        DoubanMovieTop250RsConverter top250RsConverter = new DoubanMovieTop250RsConverter();
        List<DoubanMovieTop250RsVo> doubanMovieTop250RsVos = top250RsConverter.createFromEntities(doubanMovieList);

        return doubanMovieTop250RsVos;
    }

    @Override
    public List<DoubanMovie> findDoubanMovieByIntheaters() {
        return doubanMovieDao.findDoubanMovieByIntheaters();
    }

    @Override
    public List<DoubanMovieIntheatersRsVo> findDoubanMovieByIntheaters(int start, int count) {
        List<DoubanMovie> doubanMovieList = doubanMovieDao.findDoubanMovieByIntheatersPage(start, count);
        DoubanMovieIntheatersRsConverter intheatersRsConverter = new DoubanMovieIntheatersRsConverter();
        List<DoubanMovieIntheatersRsVo> doubanMovieIntheatersRsVos = intheatersRsConverter.createFromEntities(doubanMovieList);

        return doubanMovieIntheatersRsVos;
    }

}
