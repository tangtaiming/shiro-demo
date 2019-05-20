package com.application.ttm.dao;

import com.application.ttm.entity.DoubanMovie;

import java.util.List;
import java.util.Map;

public interface DoubanMovieDao {

    DoubanMovie createDoubanMovie(DoubanMovie movie);
    DoubanMovie updateDoubanMovie(DoubanMovie movie);
    boolean deleteDoubanMovie(Long id);

    List<DoubanMovie> findAll();
    List<DoubanMovie> findList(Map<String, Object> param);
    DoubanMovie findOne(Long id);


}
