package com.application.ttm.dao;

import com.application.ttm.entity.Resource;

import java.util.List;

/**
 * <p>@Author tangtaiming</p>
 * <p>@Date 2018-12-27</p>
 * <p>@Version 1.0</p>
 **/
public interface ResourceDao {

    public Resource createResource(Resource resource);
    public Resource updateResource(Resource resource);
    public void deleteResource(Long resourceId);

    Resource findOne(Long resourceId);
    List<Resource> findAll();

    /**
     * 父类别id查询 资源
     * @param parentId
     * @return
     */
    List<Resource> findByParentId(Long parentId);

}
