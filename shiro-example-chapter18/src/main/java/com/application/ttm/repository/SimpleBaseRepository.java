package com.application.ttm.repository;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.*;

/**
 * <p>@Author tangtaiming</p>
 * <p>@Date 2019-06-19</p>
 * <p>@Version 1.0</p>
 **/
public class SimpleBaseRepository<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {

    private Class<T> domainClass;

    public SimpleBaseRepository(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
        this.domainClass = domainClass;
    }

    @Override
    public T fetchOne(Map<String, Object> requestArgs) {
        Specification specification = new SimpleSpecification(requestArgs);
        return (T) findOne(specification);
    }

    @Override
    public List<T> fetchList(Map<String, Object> requestArgs) {
        Specification specification = new SimpleSpecification(requestArgs);
        return findAll(specification);
    }

    @Override
    public boolean save(Map<String, Object> requestArgs) {
        return false;
    }

    @Override
    public boolean batchSave(List<Map<String, Object>> requestArgs) {
        return false;
    }

    @Override
    public boolean update(Map<String, Object> requestArgs) {
        return false;
    }

    public static class SimpleSpecification<T> implements Specification<T> {

        private Map<String, Object> request;

        SimpleSpecification(Map<String, Object> request) {
            this.request = request;
        }

        @Override
        public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
            List<Predicate> predicates = new ArrayList<>();
            Set<Map.Entry<String, Object>> entrys = request.entrySet();
            for (Map.Entry entry : entrys) {
                String key = (String) entry.getKey();
                Object value = entry.getValue();
                if (value instanceof Map) {
                    Map<String, Object> compareMap = (Map<String, Object>) value;
                    Set<Map.Entry<String, Object>> compareEntrys = compareMap.entrySet();
                    for (Map.Entry compareEntry : compareEntrys) {
                        String compareKey = (String) compareEntry.getKey();
                        Object compareValue = compareEntry.getValue();
                        switch (compareKey) {
                            case "$ge":
                                predicates.add(criteriaBuilder.ge(root.get(compareKey), (Number) compareValue));
                                break;
                            case "$le":
                                predicates.add(criteriaBuilder.le(root.get(compareKey), (Number) compareValue));
                                break;
                            case "$gt":
                                predicates.add(criteriaBuilder.gt(root.get(compareKey), (Number) compareValue));
                                break;
                            case "$lt":
                                predicates.add(criteriaBuilder.lt(root.get(compareKey), (Number) compareValue));
                                break;
                            case "$in":
                                predicates.add(criteriaBuilder.in(root.get(compareKey)).in((Collection<?>) compareValue));
                                break;
                            case "$like":
                                predicates.add(criteriaBuilder.like(root.get(compareKey), "%" + compareValue + "%"));
                                break;
                            case "$null":
                                predicates.add(criteriaBuilder.isNull(root.get(compareKey)));
                                break;
                            case "$not_null":
                                predicates.add(criteriaBuilder.isNotNull(root.get(compareKey)));
                                break;
                            default:
                                break;
                        }
                    }
                } else {
                    predicates.add(criteriaBuilder.equal(root.get(key), value));
                }
            }
            if (!CollectionUtils.isEmpty(predicates)) {
                criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
            }
            return criteriaQuery.getRestriction();
        }
    }

}
