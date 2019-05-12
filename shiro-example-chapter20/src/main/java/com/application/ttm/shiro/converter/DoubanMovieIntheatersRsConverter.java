package com.application.ttm.shiro.converter;

import com.application.ttm.shiro.entity.DoubanMovie;
import com.application.ttm.shiro.web.vo.DoubanMovieIntheatersRsVo;

/**
 * <p>@Author tangtaiming</p>
 * <p>@Date 2019/5/12 23:26</p>
 * <p>@Version 1.0</p>
 */
public class DoubanMovieIntheatersRsConverter extends Converter<DoubanMovieIntheatersRsVo, DoubanMovie> {

    public DoubanMovieIntheatersRsConverter() {
        super(intheatersRsVo -> {
            DoubanMovie doubanMovie = new DoubanMovie(intheatersRsVo.getTitle(),
                    intheatersRsVo.getSmallImage(),
                    intheatersRsVo.getDoubenInTheatersId(),
                    intheatersRsVo.getYear(),
                    intheatersRsVo.getStars(),
                    intheatersRsVo.getAverage(),
                    intheatersRsVo.getOriginalTitle());

            return doubanMovie;
        }, doubanMovie -> {
            DoubanMovieIntheatersRsVo intheaters = new DoubanMovieIntheatersRsVo(doubanMovie.getTitle(),
                    doubanMovie.getSmallImage(),
                    doubanMovie.getDoubenInTheatersId(),
                    doubanMovie.getYear(),
                    doubanMovie.getStars(),
                    doubanMovie.getAverage(),
                    doubanMovie.getOriginalTitle());
            return intheaters;
        });
    }
}
