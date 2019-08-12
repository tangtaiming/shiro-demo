package com.application.ttm.service.impl;

import com.application.ttm.JsonUtils;
import com.application.ttm.PageUtils;
import com.application.ttm.SpringUtils;
import com.application.ttm.dao.DoubanMovieDao;
import com.application.ttm.entity.DoubanMovie;
import com.application.ttm.repository.impl.DoubanMovieRepository;
import com.application.ttm.service.DoubanMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

@Service
public class DoubanMovieServiceImpl implements DoubanMovieService {

    @Autowired
    private DoubanMovieDao doubanMovieDao;

    @Autowired
    private DoubanMovieRepository doubanMovieRepository;

    @Override
    public List<DoubanMovie> findList(Map<String, Object> param) {
        //分页
        int pageSize = getNumPerPage(param);
        int first = (getPageNum(param) - 1);
        //排序 默認使用 id倒序
        Pageable pageable = new PageRequest(first, pageSize, new Sort(Sort.Direction.DESC, "id"));

        //查询条件
        param.remove("numPerPage");
        param.remove("pageNum");
        param.remove("orderField");
        param.remove("orderDirection");
        param.remove("_");
//        Map<String, Object> query = getQuery(param);

//        return doubanMovieDao.findList(first, pageSize);
        return doubanMovieRepository.fetchList(param, pageable);
    }

    @Override
    public int count() {
        return doubanMovieDao.count();
    }

    @Override
    public int getPageNum(Map<String, Object> param) {
        return PageUtils.getPageNum(param);
    }

    @Override
    public int getNumPerPage(Map<String, Object> param) {
        return PageUtils.getNumPerPage(param);
    }

}
