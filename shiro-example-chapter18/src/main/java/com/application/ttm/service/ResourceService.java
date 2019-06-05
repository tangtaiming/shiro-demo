package com.application.ttm.service;

import com.application.ttm.entity.Resource;

import java.util.List;
import java.util.Set;

/**
 * <p>@Author tangtaiming</p>
 * <p>@Date 2018-12-27</p>
 * <p>@Version 1.0</p>
 **/
public interface ResourceService {

    public Resource createResource(Resource resource);
    public Resource updateResource(Resource resource);
    public void deleteResource(Long resourceId);

    public Resource findOne(Long resourceId);
    List<Resource> findAll();

    /**
     * 得到资源对应的权限字符串
     * @param resourceIds
     * @return
     */
    Set<String> findPermissions(Set<Long> resourceIds);

    /**
     * 根据用户权限得到菜单
     * @param permissions
     * @return
     */
    List<Resource> findMenus(Set<String> permissions);

    /**
     * 获取用户菜单列表
     * @param userId
     * @return
     */
    List<Resource> findUserMenus(Long userId);

    /**
     * 父类别查询 资源
     * @param parentId
     * @param userMenuIdList
     * @return
     */
    List<Resource> findByParentId(Long parentId, List<Long> userMenuIdList);

}
