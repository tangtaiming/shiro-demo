package com.application.ttm.service.impl;

import com.application.ttm.PageUtils;
import com.application.ttm.dao.DoubanMovieDao;
import com.application.ttm.entity.DoubanMovie;
import com.application.ttm.service.DoubanMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DoubanMovieServiceImpl implements DoubanMovieService {

    @Autowired
    private DoubanMovieDao doubanMovieDao;

    @Override
    public List<DoubanMovie> findList(Map<String, Object> param) {
        int pageSize = getNumPerPage(param);
        int first = (getPageNum(param) - 1) * pageSize;

        return doubanMovieDao.findList(first, pageSize);
    }

    @Override
    public int count() {
        return doubanMovieDao.count();
    }

    @Override
    public int getPageNum(Map<String, Object> param) {
        return PageUtils.getPageNum(param);
    }

    @Override
    public int getNumPerPage(Map<String, Object> param) {
        return PageUtils.getNumPerPage(param);
    }

}
