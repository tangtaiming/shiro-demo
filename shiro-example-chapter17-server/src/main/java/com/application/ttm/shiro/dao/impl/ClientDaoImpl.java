package com.application.ttm.shiro.dao.impl;

import com.application.ttm.shiro.dao.ClientDao;
import com.application.ttm.shiro.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

/**
 * <p>@Author tangtaiming</p>
 * <p>@Date 2019-04-04</p>
 * <p>@Version 1.0</p>
 **/
@Repository
public class ClientDaoImpl implements ClientDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 创建Client
     *
     * @param client
     * @return
     */
    @Override
    public Client createClient(Client client) {
        final String sql = "insert into oauth2_client(client_name, client_id, client_secret) values(?,?,?)";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, client.getClientName());
            ps.setString(2, client.getClientId());
            ps.setString(3, client.getClientSecret());

            return ps;
        }, keyHolder);

        client.setId(keyHolder.getKey().longValue());
        return client;
    }

    @Override
    public Client updateClient(Client client) {
        final String sql = "update oauth2_client set client_name=?, client_id=?, client_secret=? where id=?";
        jdbcTemplate.update(
                sql,
                client.getClientName(), client.getClientId(), client.getClientSecret(), client.getId());
        return client;
    }

    @Override
    public void deleteClient(Long clientId) {
        final String sql = "delete from oauth2_client where id=?";
        jdbcTemplate.update(sql, clientId);
    }

    @Override
    public Client findOne(Long clientId) {
        String sql = "select id, client_name, client_id, client_secret from oauth2_client where id=?";
        List<Client> clientList = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Client.class), clientId);
        if(clientList.size() == 0) {
            return null;
        }
        return clientList.get(0);
    }

    @Override
    public List<Client> findAll() {
        String sql = "select id, client_name, client_id, client_secret from oauth2_client";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper(Client.class));
    }

    @Override
    public Client findByClientId(String clientId) {
        String sql = "select id, client_name, client_id, client_secret from oauth2_client where client_id=?";
        List<Client> clientList = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Client.class), clientId);
        if(clientList.size() == 0) {
            return null;
        }
        return clientList.get(0);
    }

    @Override
    public Client findByClientSecret(String clientSecret) {
        String sql = "select id, client_name, client_id, client_secret from oauth2_client where client_secret=?";
        List<Client> clientList = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Client.class), clientSecret);
        if(clientList.size() == 0) {
            return null;
        }
        return clientList.get(0);
    }
}
