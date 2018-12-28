package com.application.ttm.dao.impl;

import com.application.ttm.dao.RoleDao;
import com.application.ttm.entity.Role;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.PreparedStatement;

/**
 * <p>@Author tangtaiming</p>
 * <p>@Date 2018-12-25</p>
 * <p>@Version 1.0</p>
 **/
public class RoleDaoImpl extends JdbcDaoSupport implements RoleDao {

    @Override
    public Role createRole(Role role) {
        final String sql = "insert into shiro_sys_roles(role, description, available) values(?,?,?)";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        getJdbcTemplate().update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, role.getRole());
            ps.setString(2, role.getDescription());
            ps.setBoolean(3, role.getAvailable());
            return ps;
        }, keyHolder);

        role.setId(keyHolder.getKey().longValue());
        return role;
    }

    @Override
    public void deleteRole(Long roleId) {
        //首先把和role关联的相关表数据删掉
        String sql = "delete from shiro_sys_users_roles where role_id=?";
        getJdbcTemplate().update(sql, roleId);

        sql = "delete from shiro_sys_roles where id=?";
        getJdbcTemplate().update(sql, roleId);
    }

    @Override
    public void correlationPermissions(Long roleId, Long... permissionIds) {
        if(permissionIds == null || permissionIds.length == 0) {
            return;
        }
        String sql = "insert into shiro_sys_roles_permissions(role_id, permission_id) values(?,?)";
        for(Long permissionId : permissionIds) {
            if(!exists(roleId, permissionId)) {
                getJdbcTemplate().update(sql, roleId, permissionId);
            }
        }
    }

    @Override
    public void uncorrelationPermissions(Long roleId, Long... permissionIds) {
        if(permissionIds == null || permissionIds.length == 0) {
            return;
        }
        String sql = "delete from shiro_sys_roles_permissions where role_id=? and permission_id=?";
        for(Long permissionId : permissionIds) {
            if(exists(roleId, permissionId)) {
                getJdbcTemplate().update(sql, roleId, permissionId);
            }
        }
    }

    private boolean exists(Long roleId, Long permissionId) {
        String sql = "select count(1) from shiro_sys_roles_permissions where role_id=? and permission_id=?";
        return getJdbcTemplate().queryForObject(sql, Integer.class, roleId, permissionId) != 0;
    }

}
