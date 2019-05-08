package com.application.ttm.shiro.service;

import com.application.ttm.shiro.entity.DoubanIntheaters;

import java.util.List;

/**
 * <p>@Author tangtaiming</p>
 * <p>@Date 2019-05-08</p>
 * <p>@Version 1.0</p>
 **/
public interface DoubanIntheatersService {

    public DoubanIntheaters findOne(Long id);
    public List<DoubanIntheaters> findAll();

}
