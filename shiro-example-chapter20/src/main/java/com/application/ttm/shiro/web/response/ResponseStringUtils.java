package com.application.ttm.shiro.web.response;

import com.application.ttm.shiro.JsonUtils;

public class ResponseStringUtils {

    /**
     * 失败字符串
     * @return
     */
    public static String fail() {
        return JsonUtils.toJson(ResponseUtils.fail());
    }

}
