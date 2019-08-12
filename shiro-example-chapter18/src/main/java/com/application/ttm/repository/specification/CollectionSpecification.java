package com.application.ttm.repository.specification;

import com.application.ttm.web.session.Condition;
import javafx.beans.binding.DoubleExpression;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

/**
 * <p>@Author tangtaiming</p>
 * <p>@Date 2019-07-23 21:14</p>
 * <p>@Version 1.0</p>
 **/
public class CollectionSpecification<T> implements Specification<T> {

    private Map<String, Object> request;
    private String ormClass;

    public CollectionSpecification(Map<String, Object> request) {
        this.request = request;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        Set<Map.Entry<String, Object>> entrys = request.entrySet();
        for (Map.Entry entry : entrys) {
            String key = (String) entry.getKey();
            Object value = entry.getValue();
            List<String> compareResult = parseSimpleExpressionCompare(key);
            key = compareResult.get(0);
            String compare = compareResult.get(1);
            List<String> castResult = null;
            try {
                castResult = parseSimpleExpressionCast(key);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
            key = castResult.get(0);
            String cast = castResult.get(1);
            System.out.println("key = " + key + ", compare = " + compare + ", cast = " + cast);
            switch (compare) {
                case "$ge":
                    if (cast.equals("int") || cast.equals("double")) {
                        predicates.add(criteriaBuilder.ge(root.get(key), (Number) value));
                    } else {
                        predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(key).as(String.class), (String) value));
                    }
                    break;
                case "$le":
                    if (cast.equals("int") || cast.equals("double")) {
                        predicates.add(criteriaBuilder.le(root.get(key), (Number) value));
                    } else {
                        predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(key).as(String.class), (String) value));
                    }
                    break;
                case "$gt":
                    predicates.add(criteriaBuilder.gt(root.get(key), (Number) value));
                    break;
                case "$lt":
                    predicates.add(criteriaBuilder.lt(root.get(key), (Number) value));
                    break;
                case "$in":
                    predicates.add(criteriaBuilder.in(root.get(key)).value(value));
                    break;
                case "$like":
                    predicates.add(criteriaBuilder.like(root.get(key), "%" + value + "%"));
                    break;
                case "$null":
                    predicates.add(criteriaBuilder.isNull(root.get(key)));
                    break;
                case "$not_null":
                    predicates.add(criteriaBuilder.isNotNull(root.get(key)));
                    break;
                default:
                    predicates.add(criteriaBuilder.equal(root.get(key), value));
                    break;
            }
        }
        if (!CollectionUtils.isEmpty(predicates)) {
            criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
        }
        return criteriaQuery.getRestriction();
    }

    /**
     * 比较
     * @param param
     * @return
     */
    private List<String> parseSimpleExpressionCompare(String param) {
        String compare = "$eq";
        if (param.endsWith("[from]")) {
            param = param.substring(0, param.indexOf("[from]"));
            compare = "$ge";
        } else if (param.endsWith("[to]")) {
            param = param.substring(0, param.indexOf("[to]"));
            compare = "$le";
        } else if (param.endsWith("[like]")) {
            param = param.substring(0, param.indexOf("[like]"));
            compare = "$like";
        } else if (param.endsWith("[null]")) {
            param = param.substring(0, param.indexOf("[null]"));
            compare = "$null";
        } else if (param.endsWith("[not_null]")) {
            param = param.substring(0, param.indexOf("[not_null]"));
            compare = "$not_null";
        } else if (param.endsWith("[true]")) {
            param = param.substring(0, param.indexOf("[true]"));
            compare = "$true";
        } else if (param.endsWith("[not_true]")) {
            param = param.substring(0, param.indexOf("[not_true]"));
            compare = "$not_true";
        } else if (param.endsWith("[in]")) {
            param = param.substring(0, param.indexOf("[in]"));
            compare = "$in";
        }

        List<String> result = new ArrayList<String>();
        result.add(param);
        result.add(compare);
        return result;
    }

    /**
     * 类型
     * @param param
     * @return
     * @throws ClassNotFoundException
     * @throws NoSuchFieldException
     */
    private List<String> parseSimpleExpressionCast(String param) throws ClassNotFoundException, NoSuchFieldException {
        List<String> result = new ArrayList<String>();
        String cast;
        if (param.endsWith("[int]")) {
            param = param.substring(0, param.indexOf("[int]"));
            cast = "int";
        } else if (param.endsWith("[long]")) {
            param = param.substring(0, param.indexOf("[long]"));
            cast = "long";
        } else if (param.endsWith("[float]")) {
            param = param.substring(0, param.indexOf("[float]"));
            cast = "float";
        } else if (param.endsWith("[double]")) {
            param = param.substring(0, param.indexOf("[double]"));
            cast = "double";
        } else if (param.endsWith("[string]")) {
            param = param.substring(0, param.indexOf("[string]"));
            cast = "string";
        } else {
            String ormClass = getOrmClass();
            if (null == ormClass) {
                cast = "string";
            } else {
                cast = Class.forName(ormClass).getDeclaredField(param).getType().getName();
            }
        }
        result.add(param);
        result.add(cast);

        return result;
    }

    public String getOrmClass() {
        return ormClass;
    }

    public void setOrmClass(String ormClass) {
        this.ormClass = ormClass;
    }
}
