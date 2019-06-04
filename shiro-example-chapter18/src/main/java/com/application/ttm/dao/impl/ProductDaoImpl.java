package com.application.ttm.dao.impl;

import com.application.ttm.dao.ProductDao;
import com.application.ttm.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Product create(Product entity) {
        return null;
    }

    @Override
    public Product update(Product entity) {
        return null;
    }

    @Override
    public boolean delete(Product entity) {
        return false;
    }

    @Override
    public Product findOne(Integer id) {
        return null;
    }

    @Override
    public Product findOne(Long id) {
        return null;
    }

    @Override
    public List<Product> findList(int first, int pageSize) {
        return null;
    }

    @Override
    public int count() {
        return 0;
    }

}
