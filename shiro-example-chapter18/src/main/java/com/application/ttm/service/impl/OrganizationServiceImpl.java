package com.application.ttm.service.impl;

import com.application.ttm.dao.OrganizationDao;
import com.application.ttm.entity.Organization;
import com.application.ttm.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>@Author tangtaiming</p>
 * <p>@Date 2019-01-07</p>
 * <p>@Version 1.0</p>
 **/
@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationDao organizationDao;

    @Override
    public Organization createOrganization(Organization organization) {
        return organizationDao.createOrganization(organization);
    }

    @Override
    public Organization updateOrganization(Organization organization) {
        return organizationDao.updateOrganization(organization);
    }

    @Override
    public void deleteOrganization(Long organizationId) {
        organizationDao.deleteOrganization(organizationId);
    }

    @Override
    public Organization findOne(Long organizationId) {
        return organizationDao.findOne(organizationId);
    }

    @Override
    public List<Organization> findAll() {
        return organizationDao.findAll();
    }

    @Override
    public Object findAllWithExclude(Organization excludeOraganization) {
        return organizationDao.findAllWithExclude(excludeOraganization);
    }

    @Override
    public void move(Organization source, Organization target) {
        organizationDao.move(source, target);
    }

}
