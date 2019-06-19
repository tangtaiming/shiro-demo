package com.application.ttm.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * <p>@Author tangtaiming</p>
 * <p>@Date 2019-06-19</p>
 * <p>@Version 1.0</p>
 **/
@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends PagingAndSortingRepository<T, ID> {

    T fetchOne(Map<String, Object> requestArgs);

    List<T> fetchList(Map<String, Object> requestArgs);

    boolean save(Map<String, Object> requestArgs);

    boolean batchSave(List<Map<String, Object>> requestArgs);

    boolean update(Map<String, Object> requestArgs);

}