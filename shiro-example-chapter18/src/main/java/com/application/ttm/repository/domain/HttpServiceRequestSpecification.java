package com.application.ttm.repository.domain;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;

/**
 * http 请求
 * <p>@Author tangtaiming</p>
 * <p>@Date 2019-08-12</p>
 * <p>@Version 1.0</p>
 **/
public class HttpServiceRequestSpecification implements Specification {

    HttpServiceRequestSpecification() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest servletRequest = servletRequestAttributes.getRequest();
        servletRequest.getParameterNames();
    }

    @Override
    public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
        return null;
    }

}
