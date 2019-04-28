package com.application.ttm;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回结果
 */
public class ResponseUtils {

    public static String buildResponse(int statusCode, String message, String navTabId,
                                String rel, String callbackType, String forwardUrl) {
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("statusCode", statusCode);
        responseMap.put("message", message);
        responseMap.put("navTabId", navTabId);
        responseMap.put("rel", rel);
        responseMap.put("callbackType", callbackType);
        responseMap.put("forwardUrl", forwardUrl);
        return JsonUtils.toJson(responseMap);
    }

    /**
     * 成功
     * @return
     */
    public static String success() {
        return buildResponse(200, "操作成功", null,
                null, null, null);
    }

    public static String success(String navTabId, String forwardUrl) {
        return buildResponse(200, "操作成功", navTabId, "", "", forwardUrl);
    }

    public static String success(String navTabId) {
        return buildResponse(200, "操作成功", navTabId, "", "closeCurrent", "");
    }

}
