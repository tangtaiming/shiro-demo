package com.application.ttm.shiro.web.controller;

import com.application.ttm.shiro.entity.DoubanMovie;
import com.application.ttm.shiro.service.DoubanMovieService;
import com.application.ttm.shiro.web.response.ResponseUtils;
import com.application.ttm.shiro.web.vo.DoubanMovieIntheatersRsVo;
import com.application.ttm.shiro.web.vo.DoubanMovieTop250RsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>@Author tangtaiming</p>
 * <p>@Date 2019/5/11 23:58</p>
 * <p>@Version 1.0</p>
 */
@RestController
@RequestMapping("/doubanMovie")
public class DoubanMovieController {

    @Autowired
    private DoubanMovieService doubanMovieService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> doubanMovie() {
        List<DoubanMovie> doubanMovieList = doubanMovieService.findAll();
        return ResponseUtils.success(doubanMovieList);
    }

    /**
     * top250
     * @param start 起使位置
     * @param count 每页数量
     * @return
     */
    @RequestMapping(value = "/top250", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> top250(@RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "count", defaultValue = "20") int count) {
        List<DoubanMovieTop250RsVo> doubanMovieList = doubanMovieService.findDoubanMovieByTop250(start, count);

//        List<DoubanMovie> doubanMovieList = doubanMovieService.findDoubanMovieByTop250();
//                doubanMovieService.findDoubanMovieByTop250(start, count);
        return ResponseUtils.success(doubanMovieList);
    }

    /**
     * 热门电影
     * @return
     */
    @RequestMapping(value = "/intheaters", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> intheaters(@RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "count", defaultValue = "20") int count) {
        List<DoubanMovieIntheatersRsVo> doubanMovieList = doubanMovieService.findDoubanMovieByIntheaters(start, count);
        return ResponseUtils.success(doubanMovieList);
    }

}
