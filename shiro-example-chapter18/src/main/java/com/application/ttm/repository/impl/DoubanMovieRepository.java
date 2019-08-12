package com.application.ttm.repository.impl;

import com.application.ttm.entity.DoubanMovie;
import com.application.ttm.repository.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * 豆瓣电影
 */
@Repository
public interface DoubanMovieRepository extends BaseRepository<DoubanMovie, Long> {

}
