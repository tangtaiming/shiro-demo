package com.application.ttm.dao.impl;

import com.application.ttm.dao.OrganizationDao;
import com.application.ttm.entity.Organization;
import com.mysql.cj.jdbc.CallableStatementWrapper;
import org.springframework.beans.factory.annotation.Autowired;
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
        //add sql
        final String sql = "insert into sys_organization( name, parent_id, parent_ids, available) values(?,?,?,?)";

        //generated
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, organization.getName());
            ps.setLong(2, organization.getParentId());
            ps.setString(3, organization.getParentIds());
            ps.setBoolean(4, organization.getAvailable());
            return ps;
        }, keyHolder);

        //set id
        organization.setId(keyHolder.getKey().longValue());
        return organization;
    }

    @Override
    public Organization updateOrganization(Organization organization) {
        final String sql = "update sys_organization set name=?, parent_id=?, parent_ids=?, available=? where id=?";

        jdbcTemplate.update(sql,
                organization.getName(), organization.getParentId(), organization.getParentIds(), organization.getAvailable(), organization.getId());
        return organization;
    }

    @Override
    public void deleteOrganization(Long organizationId) {

    }

    @Override
    public Organization findOne(Long organizationId) {
        return null;
    }

    @Override
    public List<Organization> findAll() {
        return null;
    }

    @Override
    public List<Organization> findAllWithExclude(Organization excludeOraganization) {
        return null;
    }

    @Override
    public void move(Organization source, Organization target) {

    }

}
