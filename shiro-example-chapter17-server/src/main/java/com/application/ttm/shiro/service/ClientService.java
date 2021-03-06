package com.application.ttm.shiro.service;

import com.application.ttm.shiro.entity.Client;

import java.util.List;

/**
 * <p>@Author tangtaiming</p>
 * <p>@Date 2019-04-04</p>
 * <p>@Version 1.0</p>
 **/
public interface ClientService {

    Client createClient(Client client);
    Client updateClient(Client client);
    void deleteClient(Long clientId);

    Client findOne(Long clientId);

    List<Client> findAll();

    Client findByClientId(String clientId);
    Client findByClientSecret(String clientSecret);

}
