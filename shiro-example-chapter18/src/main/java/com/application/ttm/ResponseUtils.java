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
     * 成功 根据对于 callback type 返回到对应 forwardUrl
     * @param callbackType
     * @param forwardUrl
     * @return
     */
    public static String successByCallbackTypeAndForwardUrl(String callbackType, String forwardUrl) {
        return buildResponse(200, "操作成功", null,
                null, callbackType, null);
    }

    /**
     * 成功 call back typ 类型是 forward 返回到 对于 forwardUrl
     * @param forwardUrl    返回地址
     * @return
     */
    public static String successByForwardUrl(String forwardUrl) {
        return successByCallbackTypeAndForwardUrl("forward", forwardUrl);
    }

    /**
     * dialog 成功并且关闭弹窗 刷新指定 navTabId 到 forwardUrl
     * @param navTabId
     * @param forwardUrl
     * @return
     */
    public static String successByDialogCloseCurrent(String navTabId, String forwardUrl) {
        return buildResponse(200, "操作成功", navTabId,
                null, "closeCurrent", forwardUrl);
    }

    /**
     * dialog 成功并且关闭弹窗
     * @return
     */
    public static String successByDialogCloseCurrent() {
        return buildResponse(200, "操作成功", null,
                null, "closeCurrent", null);
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
