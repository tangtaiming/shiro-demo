package com.application.ttm.dao;

import com.application.ttm.entity.DoubanIntheaters;
import com.application.ttm.entity.Role;

import java.util.List;

public interface DoubanIntheatersDao {

    public DoubanIntheaters createDoubanIntheaters(DoubanIntheaters doubanIntheaters);
    public DoubanIntheaters updateDoubanIntheaters(DoubanIntheaters doubanIntheaters);
    public void deleteDoubanIntheaters(Long id);

    public DoubanIntheaters findOne(Long id);
    public List<DoubanIntheaters> findAll();

}
