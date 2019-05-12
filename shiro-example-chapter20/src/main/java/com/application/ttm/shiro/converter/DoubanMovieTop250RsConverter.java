package com.application.ttm.shiro.converter;

import com.application.ttm.shiro.entity.DoubanMovie;
import com.application.ttm.shiro.web.vo.DoubanMovieTop250RsVo;

/**
 * <p>@Author tangtaiming</p>
 * <p>@Date 2019/5/13 6:39</p>
 * <p>@Version 1.0</p>
 */
public class DoubanMovieTop250RsConverter extends Converter<DoubanMovieTop250RsVo, DoubanMovie> {

    public DoubanMovieTop250RsConverter() {
        super(top250RsVo -> {
            DoubanMovie doubanMovie = new DoubanMovie(top250RsVo.getTitle(),
                    top250RsVo.getSmallImage(),
                    top250RsVo.getDoubenInTheatersId(),
                    top250RsVo.getYear(),
                    top250RsVo.getStars(),
                    top250RsVo.getAverage(),
                    top250RsVo.getOriginalTitle());

            return doubanMovie;
        }, doubanMovie -> {
            DoubanMovieTop250RsVo top250RsVo = new DoubanMovieTop250RsVo(doubanMovie.getTitle(),
                    doubanMovie.getSmallImage(),
                    doubanMovie.getDoubenInTheatersId(),
                    doubanMovie.getYear(),
                    doubanMovie.getStars(),
                    doubanMovie.getAverage(),
                    doubanMovie.getOriginalTitle());
            return top250RsVo;
        });
    }

}
