package com.application.ttm.service;

import com.application.ttm.entity.DoubanMovie;

import java.util.List;
import java.util.Map;

public interface DoubanMovieService extends PageService {

    List<DoubanMovie> findList(Map<String, Object> param);

    int count();

}
