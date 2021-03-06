package com.application.ttm.shiro;

import com.application.ttm.shiro.codec.HmacSHA256Utils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>@Author tangtaiming</p>
 * <p>@Date 2019-05-08</p>
 * <p>@Version 1.0</p>
 **/
public class HmacSHA256Test {

    @Test
    public void sha256Test() {
        Map<String, Object> params = new HashMap<>();
        String key = "c97c216013b63b07dfc66d793de4e4a1";
        params.put(Constants.PARAM_USERID, "1");
//        params.put(Constants.PARAM_DIGEST, HmacSHA256Utils.digest(key, params));
        System.out.println("show sha256 " + Constants.PARAM_DIGEST + ": " + HmacSHA256Utils.digest(key, params));
    }

}
