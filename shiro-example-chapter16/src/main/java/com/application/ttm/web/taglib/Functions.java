package com.application.ttm.web.taglib;

import com.application.ttm.SpringUtils;
import com.application.ttm.entity.Role;
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

    private static RoleService roleService;

    public static RoleService getRoleService() {
        if (null == roleService) {
            roleService = SpringUtils.getBean(RoleService.class);
        }
        return roleService;
    }

}
