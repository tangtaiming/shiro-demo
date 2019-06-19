package com.application.ttm.dao;

import com.application.ttm.entity.DoubanMovie;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Map;

public interface DoubanMovieDao {

    DoubanMovie createDoubanMovie(DoubanMovie movie);
    DoubanMovie updateDoubanMovie(DoubanMovie movie);
    boolean deleteDoubanMovie(Long id);

    List<DoubanMovie> findAll();
    List<DoubanMovie> findList(int first, int pageSize);
    DoubanMovie findOne(Long id);
    int count();


}
