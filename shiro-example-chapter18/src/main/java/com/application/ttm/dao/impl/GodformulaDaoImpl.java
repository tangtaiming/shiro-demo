package com.application.ttm.dao.impl;

import com.application.ttm.dao.GodformulaDao;
import com.application.ttm.entity.Godformula;
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
 * <p>@Date 2019/5/10 23:15</p>
 * <p>@Version 1.0</p>
 */
@Repository
public class GodformulaDaoImpl implements GodformulaDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * sql 查询字段
     */
    private static final String FINDSQLCOLUMN = "id, name, rarity, image, creator, create_date, godformula_id, distribution_details";

    @Override
    public Godformula createGodformula(Godformula godformula) {
        final String sql = "insert into sys_godformula(" + FINDSQLCOLUMN + ") value(?,?,?,?,?,?,?)";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, godformula.getName());
            ps.setInt(2, godformula.getRarity());
            ps.setString(3, godformula.getImage());
            ps.setLong(4, godformula.getCreator());
            ps.setString(5, godformula.getCreateDate());
            ps.setLong(6, godformula.getGodformulaId());
            ps.setString(7, godformula.getDistributionDetails());

            return ps;
        }, keyHolder);
        return null;
    }

    @Override
    public Godformula updateGodformula(Godformula godformula) {
        final String sql = "update sys_godformula set name=?, rarity=?, image=?, creator=?, create_date=?, godformula_id=?, distribution_details=? where id=?";

        jdbcTemplate.update(sql,
                godformula.getName(),
                godformula.getRarity(),
                godformula.getImage(),
                godformula.getCreator(),
                godformula.getCreateDate(),
                godformula.getGodformulaId(),
                godformula.getDistributionDetails(),
                godformula.getId());
        return godformula;
    }

    @Override
    public boolean deleteGodformula(Integer id) {
        final String sql = "delete from sys_godformula where id=?";
        int count = jdbcTemplate.update(sql, id);

        return count > 0;
    }

    @Override
    public Godformula findOne(Integer id) {
        final String sql = "select " + FINDSQLCOLUMN + " from sys_godformula where id=?";
        List<Godformula> godformulaList = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Godformula.class), id);
        if (CollectionUtils.isEmpty(godformulaList)) {
            return null;
        }

        return godformulaList.get(0);
    }

    @Override
    public List<Godformula> findAll() {
        final String sql = "select " + FINDSQLCOLUMN + " from sys_godformula";
        List<Godformula> godformulaList = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Godformula.class));
        return CollectionUtils.isEmpty(godformulaList) ? new ArrayList<>() : godformulaList;
    }

    @Override
    public List<Godformula> findList(int first, int pageSize) {
        final String sql = "select " + FINDSQLCOLUMN + " from sys_godformula limit ?, ?";
        List<Godformula> godformulaList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Godformula.class), first, pageSize);

        return CollectionUtils.isEmpty(godformulaList) ? new ArrayList<>() : godformulaList;
    }

    @Override
    public int count() {
        final String sql = "select count(*) from sys_godformula";
        int count = jdbcTemplate.queryForObject(sql, Integer.class);

        return count;
    }
}
