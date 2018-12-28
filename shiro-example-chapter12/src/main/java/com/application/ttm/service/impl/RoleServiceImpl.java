package com.application.ttm.service.impl;

import com.application.ttm.dao.RoleDao;
import com.application.ttm.entity.Role;
import com.application.ttm.service.RoleService;

/**
 * <p>@Author tangtaiming</p>
 * <p>@Date 2018-12-26</p>
 * <p>@Version 1.0</p>
 **/
public class RoleServiceImpl implements RoleService {

    private RoleDao roleDao;

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Role createRole(Role role) {
        return roleDao.createRole(role);
    }

    @Override
    public void deleteRole(Long roleId) {
        roleDao.deleteRole(roleId);
    }

    @Override
    public void correlationPermissions(Long roleId, Long... permissionIds) {
        roleDao.correlationPermissions(roleId, permissionIds);
    }

    @Override
    public void uncorrelationPermissions(Long roleId, Long... permissionIds) {
        roleDao.uncorrelationPermissions(roleId, permissionIds);
    }

}
