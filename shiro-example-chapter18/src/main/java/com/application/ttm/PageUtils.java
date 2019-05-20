package com.application.ttm;

import org.apache.commons.lang.StringUtils;

import java.util.Map;

public class PageUtils {

    public static int getPageNum(Map<String, Object> param) {
        String pageNum = (String) param.get("pageNum");
        if (StringUtils.isEmpty(pageNum)) {
            pageNum = "1";
        }
        return Integer.valueOf(pageNum);
    }

    public static Integer getNumPerPage(Map<String, Object> param) {
        String numPerPage = (String) param.get("numPerPage");
        if (StringUtils.isEmpty(numPerPage)) {
            numPerPage = "20";
        }
        return Integer.valueOf(numPerPage);
    }

}
