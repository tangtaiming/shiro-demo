package com.application.ttm.shiro.web.response;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>@Author tangtaiming</p>
 * <p>@Date 2019-05-08</p>
 * <p>@Version 1.0</p>
 **/
public class ResponseUtils<T> {

    private int re;
    private T data;
    private String msg;

    public static <T> Map<String, Object> success(T data) {
        return buildResponse(200, data, "success");
    }

    public static Map<String, Object> success() {
        return buildResponse(200, null, "success");
    }

    public static <T> Map<String, Object> buildResponse(int re, T data, String msg) {
        Map<String, Object> params = new HashMap<>();
        params.put("re", re);
        params.put("data", data);
        params.put("msg", msg);

        return params;
    }

    public int getRe() {
        return re;
    }

    public void setRe(int re) {
        this.re = re;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
