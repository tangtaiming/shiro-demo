package com.application.ttm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * <p>@Author tangtaiming</p>
 * <p>@Date 2019-06-19</p>
 * <p>@Version 1.0</p>
 **/
public class BaseRepositoryFactory<R extends JpaRepository<T, I>, T, I extends Serializable>
        extends JpaRepositoryFactoryBean<R, T, I> {

    @Override
    protected RepositoryFactorySupport createRepositoryFactory(EntityManager em) {
        return new SimpleRepositoryFactory<>(em);
    }

    private static class SimpleRepositoryFactory<T, I extends Serializable> extends JpaRepositoryFactory {
        private final EntityManager em;

        public SimpleRepositoryFactory(EntityManager em) {
            super(em);
            this.em = em;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected Object getTargetRepository(RepositoryMetadata metadata) {
            return new SimpleBaseRepository<T, I>((Class<T>) metadata.getDomainType(), em);
        }

        @Override
        protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
            return SimpleBaseRepository.class;
        }
    }

}
