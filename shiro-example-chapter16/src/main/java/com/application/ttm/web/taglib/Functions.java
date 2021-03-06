package com.application.ttm.web.taglib;

import com.application.ttm.SpringUtils;
import com.application.ttm.entity.Resource;
import com.application.ttm.entity.Role;
import com.application.ttm.service.ResourceService;
import com.application.ttm.service.RoleService;
import org.apache.commons.collections.CollectionUtils;

import java.util.Collection;

/**
 * <p>@Author tangtaiming</p>
 * <p>@Date 2018-12-28</p>
 * <p>@Version 1.0</p>
 **/
public class Functions {

    public static String roleName(Long roleId) {
        Role role = getRoleService().findOne(roleId);

        return (null == role) ? "" : role.getDescription();
    }

    public static String roleNames(Collection<Long> roleIds) {
        if (CollectionUtils.isEmpty(roleIds)) {
            return "";
        }

        StringBuffer sb = new StringBuffer();
        for (Long roleId : roleIds) {
            Role role = getRoleService().findOne(roleId);
            if (null == role) {
                return "";
            }
            sb.append(role.getDescription());
            sb.append(",");
        }

        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    public static String resourceNames(Collection<Long> resourceIds) {
        if (CollectionUtils.isEmpty(resourceIds)) {
            return "";
        }

        StringBuffer sb = new StringBuffer();
        for (Long resourceId : resourceIds) {
            Resource resource = getResourceService().findOne(resourceId);
            if (null == resource) {
                return "";
            }
            sb.append(resource.getName());
            sb.append(",");
        }

        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    public static boolean in(Iterable iterable, Object element) {
        if (null == iterable) {
            return false;
        }
        return org.springframework.util.CollectionUtils.contains(iterable.iterator(), element);
    }

    private static RoleService roleService;

    private static ResourceService resourceService;

    public static RoleService getRoleService() {
        if (null == roleService) {
            roleService = SpringUtils.getBean(RoleService.class);
        }
        return roleService;
    }

    public static ResourceService getResourceService() {
        if (null == resourceService) {
            resourceService = SpringUtils.getBean(ResourceService.class);
        }
        return resourceService;
    }

}
