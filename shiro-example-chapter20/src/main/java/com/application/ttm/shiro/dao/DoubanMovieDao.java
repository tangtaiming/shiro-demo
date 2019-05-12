package com.application.ttm.shiro.dao;

import com.application.ttm.shiro.entity.DoubanMovie;

import java.util.List;

/**
 * <p>@Author tangtaiming</p>
 * <p>@Date 2019/5/11 23:40</p>
 * <p>@Version 1.0</p>
 */
public interface DoubanMovieDao {

    public List<DoubanMovie> findAll();
    public List<DoubanMovie> findDoubanMovieByTop250();
    public List<DoubanMovie> findDoubanMovieByTop250Page(int start, int count);

    public List<DoubanMovie> findDoubanMovieByIntheaters();
    public List<DoubanMovie> findDoubanMovieByIntheatersPage(int start, int count);

}
