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

    private static final String FINDSQLCOLUMN = "id, sku, spu, title, description, status, price, length, width, height, total_weight, creator, create_date";

    @Override
    public Product create(Product entity) {
        final String sql = "insert into sys_product(" + FINDSQLCOLUMN + ") values (?, ?)";

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
