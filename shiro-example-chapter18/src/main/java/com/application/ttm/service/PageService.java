package com.application.ttm.service;

import java.util.Map;

public interface PageService {

    int getPageNum(Map<String, Object> param);
    int getNumPerPage(Map<String, Object> param);

}
