package com.application.ttm.dao.impl;

import com.application.ttm.dao.DoubanMovie2Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.Serializable;

/**
 * <p>@Author tangtaiming</p>
 * <p>@Date 2019-06-19</p>
 * <p>@Version 1.0</p>
 **/
public class HibernateDoubanMovieDaoImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements DoubanMovie2Dao<T, ID> {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    private final Class<T> domainClass;

    public HibernateDoubanMovieDaoImpl(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
        this.domainClass = domainClass;
    }

}
