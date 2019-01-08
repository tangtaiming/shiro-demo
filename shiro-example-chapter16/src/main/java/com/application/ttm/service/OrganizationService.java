package com.application.ttm.service;

import com.application.ttm.entity.Organization;

import java.util.List;

/**
 * <p>@Author tangtaiming</p>
 * <p>@Date 2019-01-07</p>
 * <p>@Version 1.0</p>
 **/
public interface OrganizationService {

    public Organization createOrganization(Organization organization);
    public Organization updateOrganization(Organization organization);
    public void deleteOrganization(Long organizationId);

    Organization findOne(Long organizationId);
    List<Organization> findAll();

    Object findAllWithExclude(Organization excludeOraganization);

    void move(Organization source, Organization target);

}
