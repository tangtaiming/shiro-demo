package com.application.ttm.shiro.service.impl;

import com.application.ttm.shiro.dao.ClientDao;
import com.application.ttm.shiro.entity.Client;
import com.application.ttm.shiro.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>@Author tangtaiming</p>
 * <p>@Date 2019-04-04</p>
 * <p>@Version 1.0</p>
 **/
@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientDao clientDao;

    @Override
    public Client createClient(Client client) {
        return clientDao.createClient(client);
    }

    @Override
    public Client updateClient(Client client) {
        return clientDao.updateClient(client);
    }

    @Override
    public void deleteClient(Long clientId) {
        clientDao.deleteClient(clientId);
    }

    @Override
    public Client findOne(Long clientId) {
        return clientDao.findOne(clientId);
    }

    @Override
    public List<Client> findAll() {
        return clientDao.findAll();
    }

    @Override
    public Client findByClientId(String clientId) {
        return clientDao.findByClientId(clientId);
    }

    @Override
    public Client findByClientSecret(String clientSecret) {
        return clientDao.findByClientSecret(clientSecret);
    }

}
