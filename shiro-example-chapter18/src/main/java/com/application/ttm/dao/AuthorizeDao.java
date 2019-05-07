package com.application.ttm.dao;

import com.application.ttm.entity.Authorize;

import java.util.List;

public interface AuthorizeDao {

    public Authorize createAuthorize(Authorize authorize);
    public Authorize updateAuthorize(Authorize authorize);
    public void deleteAuthorize(Long id);

    public Authorize findOne(Long id);
    public List<Authorize> findAll();

}
