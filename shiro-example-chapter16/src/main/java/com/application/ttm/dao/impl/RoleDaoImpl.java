package com.application.ttm.dao.impl;

import com.application.ttm.dao.RoleDao;
import com.application.ttm.entity.Role;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

/**
 * <p>@Author tangtaiming</p>
 * <p>@Date 2018-12-27</p>
 * <p>@Version 1.0</p>
 **/
@Repository
public class RoleDaoImpl implements RoleDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Role createRole(Role role) {
        final String sql = "insert into sys_role(role, description, resource_ids, available) values(?, ?, ?, ?)";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, role.getRole());
            ps.setString(2, role.getDescription());
            ps.setString(3, role.getResourceIdsStr());
            ps.setBoolean(4, role.getAvailable());
            return ps;
        }, keyHolder);
        role.setId(keyHolder.getKey().longValue());
        return role;
    }

    @Override
    public Role updateRole(Role role) {
        final String sql = "update sys_role set role=?, description=?, resource_ids=?, available=? where id=?";

        jdbcTemplate.update(sql, role.getRole(), role.getDescription(), role.getResourceIdsStr(), role.getAvailable(), role.getId());
        return role;
    }

    @Override
    public void deleteRole(Long roleId) {
        final String sql = "delete from sys_role where id=?";
        jdbcTemplate.update(sql, roleId);
    }

    @Override
    public Role findOne(Long roleId) {
        final String sql = "select id, role, description, resource_ids as resourceIdsStr, available from sys_role where id=?";

        List<Role> roleList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Role.class), roleId);
        return CollectionUtils.isEmpty(roleList) ? null : roleList.get(0);
    }

    @Override
    public List<Role> findAll() {
        final String sql = "select id, role, description, resource_ids as resourceIdsStr, available from sys_role";

        List<Role> roleList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Role.class));
        return CollectionUtils.isEmpty(roleList) ? null : roleList;
    }

}
