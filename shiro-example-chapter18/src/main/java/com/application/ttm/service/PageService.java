package com.application.ttm.service;

import java.util.Map;

public interface PageService {

    Integer getPageNum(Map<String, Object> param);
    Integer getNumPerPage(Map<String, Object> param);

}
