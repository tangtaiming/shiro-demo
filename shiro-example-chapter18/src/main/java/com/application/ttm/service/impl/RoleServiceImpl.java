package com.application.ttm.service.impl;

import com.alibaba.druid.sql.PagerUtils;
import com.application.ttm.JsonUtils;
import com.application.ttm.PageUtils;
import com.application.ttm.dao.ResourceDao;
import com.application.ttm.dao.RoleDao;
import com.application.ttm.entity.Role;
import com.application.ttm.entity.User;
import com.application.ttm.service.PageService;
import com.application.ttm.service.ResourceService;
import com.application.ttm.service.RoleService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>@Author tangtaiming</p>
 * <p>@Date 2018-12-27</p>
 * <p>@Version 1.0</p>
 **/
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private ResourceService resourceService;

    @Override
    public Role createRole(Role role) {
        return roleDao.createRole(role);
    }

    @Override
    public Role updateRole(Role role) {
        return roleDao.updateRole(role);
    }

    @Override
    public void deleteRole(Long roleId) {
        roleDao.deleteRole(roleId);
    }

    @Override
    public Role findOne(Long roleId) {
        return roleDao.findOne(roleId);
    }

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public List<Role> findList(int pageNumber, int pageSize) {
        int index = (pageNumber - 1) * pageSize;
        return roleDao.findList(index, pageSize);
    }

    @Override
    public List<Role> findList(Map<String, Object> param) {
        Integer pageSize = Integer.valueOf(getNumPerPage(param));
        Integer first = (getPageNum(param) - 1) * pageSize;
        return roleDao.findList(first, pageSize);
    }

    @Override
    public Set<String> findRoles(Long... roleIds) {
        Set<String> roles = new HashSet<>();
        for (Long roleId : roleIds) {
            Role role = findOne(roleId);
            if (null != role) {
                roles.add(role.getRole());
            }
        }
        return roles;
    }

    @Override
    public Set<String> findPermissions(Long[] roleIds) {
        Set<Long> resourcesIds = new HashSet<>();
        for (Long roleId : roleIds) {
            Role role = findOne(roleId);
            if (null != role) {
                resourcesIds.addAll(role.getResourceIds());
            }
        }
        return resourceService.findPermissions(resourcesIds);
    }

    @Override
    public int getPageNum(Map<String, Object> param) {
        return PageUtils.getPageNum(param);
    }

    @Override
    public int getNumPerPage(Map<String, Object> param) {
        return PageUtils.getNumPerPage(param);
    }
}
