package com.application.ttm.shiro.service.impl;

import com.application.ttm.shiro.dao.DoubanIntheatersDao;
import com.application.ttm.shiro.entity.DoubanIntheaters;
import com.application.ttm.shiro.service.DoubanIntheatersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>@Author tangtaiming</p>
 * <p>@Date 2019-05-08</p>
 * <p>@Version 1.0</p>
 **/
@Service
public class DoubanIntheatersServiceImpl implements DoubanIntheatersService {

    @Autowired
    private DoubanIntheatersDao doubanIntheatersDao;

    @Override
    public DoubanIntheaters findOne(Long id) {
        return doubanIntheatersDao.findOne(id);
    }

    @Override
    public List<DoubanIntheaters> findAll() {
        return doubanIntheatersDao.findAll();
    }
}
