package com.application.ttm.dao.impl;

import com.application.ttm.dao.ProductDao;
import com.application.ttm.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String FINDSQLCOLUMN = "sku, spu, title, description, status, price, length, width, height, total_weight, creator, create_date";

    @Override
    public Product create(Product entity) {
        final String sql = "insert into sys_product("+ FINDSQLCOLUMN + ") values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, entity.getSku());
            ps.setString(2, entity.getSpu());
            ps.setString(3, entity.getTitle());
            ps.setString(4, entity.getDescription());
            ps.setInt(5, entity.getStatus());
            ps.setDouble(6, entity.getPrice());
            ps.setDouble(7, entity.getLength());
            ps.setDouble(8, entity.getWidth());
            ps.setDouble(9, entity.getHeight());
            ps.setDouble(10, entity.getTotalWeight());
            ps.setLong(11, entity.getCreator());
            ps.setString(12, entity.getCreateDate());

            return ps;
        }, keyHolder);

        entity.setId(keyHolder.getKey().longValue());
        return entity;
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
