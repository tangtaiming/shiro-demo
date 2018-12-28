package com.application.ttm.dao;

import com.application.ttm.entity.Permission;

/**
 * <p>@Author tangtaiming</p>
 * <p>@Date 2018-12-25</p>
 * <p>@Version 1.0</p>
 **/
public interface PermissionDao {

    public Permission createPermission(Permission permission);

    public void deletePermission(Long permissionId);

}
