package com.application.ttm.dao.impl;

import com.application.ttm.dao.PermissionDao;
import com.application.ttm.entity.Permission;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.PreparedStatement;

/**
 * <p>@Author tangtaiming</p>
 * <p>@Date 2018-12-25</p>
 * <p>@Version 1.0</p>
 **/
public class PermissionDaoImpl extends JdbcDaoSupport implements PermissionDao {

    @Override
    public Permission createPermission(Permission permission) {
        final String sql = "insert into shiro_sys_permissions(permission, description, available) values(?,?,?)";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        getJdbcTemplate().update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, permission.getPermission());
            ps.setString(2, permission.getDescription());
            ps.setBoolean(3, permission.getAvailable());
            return ps;
        }, keyHolder);

        permission.setId(keyHolder.getKey().longValue());
        return permission;
    }

    @Override
    public void deletePermission(Long permissionId) {
        //首先把与permission关联的相关表的数据删掉
        String sql = "delete from shiro_sys_roles_permissions where permission_id=?";
        getJdbcTemplate().update(sql, permissionId);

        sql = "delete from shiro_sys_permissions where id=?";
        getJdbcTemplate().update(sql, permissionId);
    }

}
