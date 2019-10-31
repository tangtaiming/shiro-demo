package com.application.ttm.web.taglib;

import com.application.ttm.SpringUtils;
import com.application.ttm.entity.Resource;
import com.application.ttm.entity.Role;
import com.application.ttm.service.ResourceService;
import com.application.ttm.service.RoleService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

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

    public static String doubanIntheatersReplaceImage(String image) {
        return StringUtils.replace(image, ".jpg", ".webp");
    }

    public static String findNameByUserId(Long userId) {
        String name = "未进行用户数据查询 TODO";
        Long systemId = -999L;
        if (systemId.equals(userId)) {
            name = "系统";
        }
        return name;
    }

    /**
     * 阴阳师稀有度名称
     * @param rarity
     * @return
     */
    public static String rarityName(Integer rarity) {
        String rarityName = null;
        switch (rarity) {
            case 1:
                rarityName = "N";
                break;
            case 2:
                rarityName = "R";
                break;
            case 3:
                rarityName = "SR";
                break;
            case 4:
                rarityName = "SSR";
                break;
            case 5:
                rarityName = "SP";
                break;
            default:
                rarityName = rarity.toString();
                break;
        }
        return rarityName;
    }

    /**
     * 阴阳师分布详情是否编辑
     * @param distributionDetails
     * @return
     */
    public static String showDistributionDetails(String distributionDetails) {
        if (StringUtils.isBlank(distributionDetails)) {
            return "";
        }
        return "已编辑";
    }

}
