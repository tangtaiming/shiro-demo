package com.application.ttm.service;

import com.application.ttm.entity.Permission;

/**
 * <p>@Author tangtaiming</p>
 * <p>@Date 2018-12-26</p>
 * <p>@Version 1.0</p>
 **/
public interface PermissionService {

    public Permission createPermission(Permission permission);
    public void deletePermission(Long permissionId);

}
