package com.application.ttm.dao;

import com.application.ttm.entity.Organization;

import java.util.List;

/**
 * <p>@Author tangtaiming</p>
 * <p>@Date 2019-01-07</p>
 * <p>@Version 1.0</p>
 **/
public interface OrganizationDao {

    public Organization createOrganization(Organization organization);
    public Organization updateOrganization(Organization organization);
    public void deleteOrganization(Long organizationId);

    Organization findOne(Long organizationId);
    List<Organization> findAll();

    List<Organization> findAllWithExclude(Organization excludeOraganization);

    void move(Organization source, Organization target);

}
