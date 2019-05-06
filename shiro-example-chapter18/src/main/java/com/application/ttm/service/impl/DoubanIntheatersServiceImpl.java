package com.application.ttm.service.impl;

import com.application.ttm.dao.DoubanIntheatersDao;
import com.application.ttm.entity.DoubanIntheaters;
import com.application.ttm.service.DoubanIntheatersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoubanIntheatersServiceImpl implements DoubanIntheatersService {

    @Autowired
    private DoubanIntheatersDao doubanIntheatersDao;

    @Override
    public DoubanIntheaters createDoubanIntheaters(DoubanIntheaters doubanIntheaters) {
        return doubanIntheatersDao.createDoubanIntheaters(doubanIntheaters);
    }

    @Override
    public DoubanIntheaters updateDoubanIntheaters(DoubanIntheaters doubanIntheaters) {
        return doubanIntheatersDao.updateDoubanIntheaters(doubanIntheaters);
    }

    @Override
    public void deleteDoubanIntheaters(Long id) {
        doubanIntheatersDao.deleteDoubanIntheaters(id);
    }

    @Override
    public DoubanIntheaters findOne(Long id) {
        return doubanIntheatersDao.findOne(id);
    }

    @Override
    public List<DoubanIntheaters> findAll() {
        return doubanIntheatersDao.findAll();
    }
}
