package com.application.ttm.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.application.ttm.dao.ResourceDao;
import com.application.ttm.entity.Resource;
import com.application.ttm.service.ResourceService;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>@Author tangtaiming</p>
 * <p>@Date 2018-12-27</p>
 * <p>@Version 1.0</p>
 **/
@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceDao resourceDao;

    @Override
    public Resource createResource(Resource resource) {
        return resourceDao.createResource(resource);
    }

    @Override
    public Resource updateResource(Resource resource) {
        return resourceDao.updateResource(resource);
    }

    @Override
    public void deleteResource(Long resourceId) {
        resourceDao.deleteResource(resourceId);
    }

    @Override
    public Resource findOne(Long resourceId) {
        return resourceDao.findOne(resourceId);
    }

    @Override
    public List<Resource> findAll() {
        return resourceDao.findAll();
    }

    @Override
    public Set<String> findPermissions(Set<Long> resourceIds) {
        Set<String> permissions = new HashSet<String>();
        for (Long resourceId : resourceIds) {
            Resource resource = findOne(resourceId);
            if (null != resource && !StringUtils.isEmpty(resource.getPermission())) {
                permissions.add(resource.getPermission());
            }
        }
        return permissions;
    }

    @Override
    public List<Resource> findMenus(Set<String> permissions) {
        List<Resource> allResources = findAll();
        List<Resource> menus = new ArrayList<>();
        for (Resource resource : allResources) {
            if (resource.isRootNode()) {
                continue;
            }

            if (resource.getType() != Resource.ResourceType.menu) {
                continue;
            }

            if (!hasPermission(permissions, resource)) {
                continue;
            }
            menus.add(resource);
        }
        return menus;
    }

    private boolean hasPermission(Set<String> permissions, Resource resource) {
        if (StringUtils.isEmpty(resource.getPermission())) {
            return true;
        }

        for (String permission : permissions) {
            WildcardPermission p1 = new WildcardPermission(permission);
            WildcardPermission p2 = new WildcardPermission(resource.getPermission());
            if (p1.implies(p2) || p2.implies(p1)) {
                return true;
            }
        }
        return false;
    }

}
