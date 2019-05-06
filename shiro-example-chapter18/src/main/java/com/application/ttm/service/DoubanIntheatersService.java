package com.application.ttm.service;

import com.application.ttm.entity.DoubanIntheaters;

import java.util.List;

public interface DoubanIntheatersService {

    public DoubanIntheaters createDoubanIntheaters(DoubanIntheaters doubanIntheaters);
    public DoubanIntheaters updateDoubanIntheaters(DoubanIntheaters doubanIntheaters);
    public void deleteDoubanIntheaters(Long id);

    public DoubanIntheaters findOne(Long id);
    public List<DoubanIntheaters> findAll();

}
