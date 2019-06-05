package com.application.ttm.dao.impl;

import com.application.ttm.dao.ResourceDao;
import com.application.ttm.entity.Resource;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>@Author tangtaiming</p>
 * <p>@Date 2018-12-27</p>
 * <p>@Version 1.0</p>
 **/
@Repository
public class ResourceDaoImpl implements ResourceDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Resource createResource(Resource resource) {
        final String sql = "insert into sys_resource(name, type, url, permission, parent_id, parent_ids, available) values(?,?,?,?,?,?,?)";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, resource.getName());
            ps.setString(2, resource.getType().name());
            ps.setString(3, resource.getUrl());
            ps.setString(4, resource.getPermission());
            ps.setLong(5, resource.getParentId());
            ps.setString(6, resource.getParentIds());
            ps.setBoolean(7, resource.getAvailable());
            return ps;
        }, keyHolder);

        resource.setId(keyHolder.getKey().longValue());
        return resource;
    }

    @Override
    public Resource updateResource(Resource resource) {
        final String sql = "update sys_resource set name=?, type=?, url=?, permission=?, parent_id=?, parent_ids=?, available=? where id=?";
        jdbcTemplate.update(sql, resource.getName(),
                resource.getType().name(),
                resource.getUrl(),
                resource.getPermission(),
                resource.getParentId(),
                resource.getParentIds(),
                resource.getAvailable(),
                resource.getId()
        );
        return resource;
    }

    @Override
    public void deleteResource(Long resourceId) {
        Resource resource = findOne(resourceId);
        final String deleteSelfSql = "delete from sys_resource where id=?";
        jdbcTemplate.update(deleteSelfSql, resourceId);
        final String deleteDescendantsSql = "delete from sys_resource where parent_ids like ?";
        jdbcTemplate.update(deleteDescendantsSql, resource.makeSelfAsParentIds() + "%");
    }

    @Override
    public Resource findOne(Long resourceId) {
        final String sql = "select id, name, type, url, permission, parent_id, parent_ids, available from sys_resource where id=?";
        List<Resource> resourceList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resource.class), resourceId);
        return CollectionUtils.isEmpty(resourceList) ? null : resourceList.get(0);
    }

    @Override
    public List<Resource> findAll() {
        final String sql = "select id, name, type, url, permission, parent_id, parent_ids, available from sys_resource";
        List<Resource> resourceList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resource.class));
        return CollectionUtils.isEmpty(resourceList) ? null : resourceList;
    }

    /**
     * 父类别id查询 资源
     *
     * @param parentId
     * @return
     */
    @Override
    public List<Resource> findByParentId(Long parentId) {
        final String sql = "select id, name, type, url, permission, parent_id, parent_ids, available from sys_resource where parent_id=?";
        List<Resource> resources = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resource.class), parentId);

        return CollectionUtils.isEmpty(resources) ? new ArrayList<>() : resources;
    }

}
