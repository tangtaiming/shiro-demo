package com.application.ttm.dao.impl;

import com.application.ttm.dao.OrganizationDao;
import com.application.ttm.entity.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

/**
 * <p>User: tangtaiming
 * <p>Date: 2019-01-06
 * <p>Version: 1.0
 */
@Repository
public class OrganizationDaoImpl implements OrganizationDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Organization createOrganization(Organization organization) {
        final String sql = "insert into sys_organization( name, parent_id, parent_ids, available) values(?,?,?,?)";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, organization.getName());
            ps.setLong(2, organization.getParentId());
            ps.setString(3, organization.getParentIds());
            ps.setBoolean(4, organization.getAvailable());
            return ps;
        }, keyHolder);

        organization.setId(keyHolder.getKey().longValue());
        return organization;
    }

    @Override
    public Organization updateOrganization(Organization organization) {
        final String sql = "update sys_organization set name=?, parent_id=?, parent_ids=?, available=? where id=?";

        jdbcTemplate.update(
                sql,
                organization.getName(), organization.getParentId(), organization.getParentIds(), organization.getAvailable(), organization.getId());
        return organization;
    }

    @Override
    public void deleteOrganization(Long organizationId) {
        Organization organization = findOne(organizationId);
        final String deleteSelfSql = "delete from sys_organization where id=?";
        jdbcTemplate.update(deleteSelfSql, organizationId);
        final String deleteDescendantsSql = "delete from sys_organization where parent_ids like ?";
        jdbcTemplate.update(deleteDescendantsSql, organization.makeSelfAsParentIds() + "%");
    }

    @Override
    public Organization findOne(Long organizationId) {
        final String sql = "select id, name, parent_id, parent_ids, available from sys_organization where id=?";
        List<Organization> organizationList = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Organization.class), organizationId);
        if(organizationList.size() == 0) {
            return null;
        }
        return organizationList.get(0);
    }

    @Override
    public List<Organization> findAll() {
        final String sql = "select id, name, parent_id, parent_ids, available from sys_organization";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper(Organization.class));
    }

    @Override
    public List<Organization> findAllWithExclude(Organization excludeOraganization) {
        //TODO 改成not exists 利用索引
        final String sql = "select id, name, parent_id, parent_ids, available from sys_organization where id!=? and parent_ids not like ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper(Organization.class), excludeOraganization.getId(), excludeOraganization.makeSelfAsParentIds() + "%");
    }

    @Override
    public void move(Organization source, Organization target) {
        String moveSourceSql = "update sys_organization set parent_id=?,parent_ids=? where id=?";
        jdbcTemplate.update(moveSourceSql, target.getId(), target.getParentIds(), source.getId());
        String moveSourceDescendantsSql = "update sys_organization set parent_ids=concat(?, substring(parent_ids, length(?))) where parent_ids like ?";
        jdbcTemplate.update(moveSourceDescendantsSql, target.makeSelfAsParentIds(), source.makeSelfAsParentIds(), source.makeSelfAsParentIds() + "%");
    }

}
