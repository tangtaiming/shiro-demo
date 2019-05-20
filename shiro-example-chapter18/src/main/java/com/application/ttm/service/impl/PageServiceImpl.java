package com.application.ttm.service.impl;

import com.application.ttm.PageUtils;
import com.application.ttm.service.PageService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PageServiceImpl implements PageService {

    @Override
    public int getPageNum(Map<String, Object> param) {
        return PageUtils.getPageNum(param);
    }

    @Override
    public int getNumPerPage(Map<String, Object> param) {
        return PageUtils.getNumPerPage(param);
    }

}
