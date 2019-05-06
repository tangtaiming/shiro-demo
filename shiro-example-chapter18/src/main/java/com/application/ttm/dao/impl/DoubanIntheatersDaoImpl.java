package com.application.ttm.dao.impl;

import com.application.ttm.dao.DoubanIntheatersDao;
import com.application.ttm.entity.DoubanIntheaters;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class DoubanIntheatersDaoImpl implements DoubanIntheatersDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public DoubanIntheaters createDoubanIntheaters(DoubanIntheaters doubanIntheaters) {
        final String sql = "insert into sys_douban_intheaters(title, small_image, douben_intheaters_id, year, stars, average) values(?,?,?,?,?,?)";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, doubanIntheaters.getTitle());
            ps.setString(2, doubanIntheaters.getSmallImage());
            ps.setLong(3, doubanIntheaters.getDoubenIntheatersId());
            ps.setString(4, doubanIntheaters.getYear());
            ps.setString(5, doubanIntheaters.getStars());
            ps.setInt(6, doubanIntheaters.getAverage());
            return ps;
        }, keyHolder);

        doubanIntheaters.setId(keyHolder.getKey().longValue());
        return doubanIntheaters;
    }

    @Override
    public DoubanIntheaters updateDoubanIntheaters(DoubanIntheaters doubanIntheaters) {
        final String sql = "update sys_douban_intheaters set title=?, small_image=?, douben_intheaters_id=?, year=?," +
                " stars=?, average=? where id=?";

        jdbcTemplate.update(sql, doubanIntheaters.getTitle(), doubanIntheaters.getSmallImage(),
                doubanIntheaters.getDoubenIntheatersId(), doubanIntheaters.getYear(), doubanIntheaters.getStars(),
                doubanIntheaters.getAverage(), doubanIntheaters.getId());
        return doubanIntheaters;
    }

    @Override
    public void deleteDoubanIntheaters(Long id) {
        final String deleteSelfSql = "delete from sys_douban_intheaters where id=?";

        jdbcTemplate.update(deleteSelfSql, id);
    }

    @Override
    public DoubanIntheaters findOne(Long id) {
        final String sql = "select id, title, small_image, douben_intheaters_id, year, stars, average from sys_douban_intheaters where id=?";

        List<DoubanIntheaters> doubanIntheaters = jdbcTemplate.query(sql, new BeanPropertyRowMapper(DoubanIntheaters.class), id);
        if(doubanIntheaters.size() == 0) {
            return null;
        }
        return doubanIntheaters.get(0);
    }

    @Override
    public List<DoubanIntheaters> findAll() {
        final String sql = "select id, title, small_image, douben_intheaters_id, year, stars, average from sys_douban_intheaters";
        List<DoubanIntheaters> doubanIntheaters = jdbcTemplate.query(sql, new BeanPropertyRowMapper(DoubanIntheaters.class));

        return doubanIntheaters;
    }

}
