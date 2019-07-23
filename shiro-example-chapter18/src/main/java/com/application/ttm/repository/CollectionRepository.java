package com.application.ttm.repository;

import java.util.List;
import java.util.Map;

/**
 * 页面参数请求
 * <p>@Author tangtaiming</p>
 * <p>@Date 2019-07-23</p>
 * <p>@Version 1.0</p>
 **/
public interface CollectionRepository<T> {

    public List<T> getCollection(Map<String, Object> params);
    public Long getCollectionCount(Map<String, Object> params);

}
