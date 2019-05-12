package com.application.ttm.shiro.service;

import com.application.ttm.shiro.entity.DoubanMovie;
import com.application.ttm.shiro.web.vo.DoubanMovieIntheatersRsVo;
import com.application.ttm.shiro.web.vo.DoubanMovieTop250RsVo;

import java.util.List;

/**
 * <p>@Author tangtaiming</p>
 * <p>@Date 2019/5/11 23:53</p>
 * <p>@Version 1.0</p>
 */
public interface DoubanMovieService {

    public List<DoubanMovie> findAll();
    public List<DoubanMovie> findDoubanMovieByTop250();
    public List<DoubanMovieTop250RsVo> findDoubanMovieByTop250(int start, int count);

    public List<DoubanMovie> findDoubanMovieByIntheaters();
    public List<DoubanMovieIntheatersRsVo> findDoubanMovieByIntheaters(int start, int count);

}
