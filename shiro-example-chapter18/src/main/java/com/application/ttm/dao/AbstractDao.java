package com.application.ttm.dao;

import java.util.List;

public interface AbstractDao<E extends Object> {

    /**
     * 创建
     * @param entity
     * @return
     */
    E create(E entity);

    /**
     * 修改
     * @param entity
     * @return
     */
    E update(E entity);

    /**
     * 删除
     * @param entity
     * @return
     */
    boolean delete(E entity);

    /**
     * id 查询数据
     * @param id
     * @return
     */
    E findOne(Integer id);

    /**
     * id 查询数据
     * @param id
     * @return
     */
    E findOne(Long id);

    /**
     * 分页查询数据
     * @param first
     * @param pageSize
     * @return
     */
    List<E> findList(int first, int pageSize);

    /**
     * 查询数量
     * @return
     */
    int count();

}
