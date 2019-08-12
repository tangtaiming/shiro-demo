package com.application.ttm.web.session;

import com.application.ttm.JsonUtils;
import com.qiniu.util.Json;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Restrictions;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * <p>@Author tangtaiming</p>
 * <p>@Date 2019-07-23</p>
 * <p>@Version 1.0</p>
 **/
public class FilterSession {

    private String ormClass;
    private Map<String, Object> filterMap;
    private Map<String, Object> filter;

//    public FilterSession(String ormClass) {
//        this.ormClass = ormClass;
//
//    }

    public FilterSession(String ormClass) throws NoSuchFieldException, ClassNotFoundException {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest servletRequest = requestAttributes.getRequest();
        HttpSession session = servletRequest.getSession();
        String requestUri = servletRequest.getRequestURI();
        String method = servletRequest.getMethod();
        //判断是否存在对于session
        filterMap = (Map<String, Object>) session.getAttribute("filter");
        if (null != filterMap) {
            filter = (Map<String, Object>) filterMap.get(requestUri);
            if (MapUtils.isEmpty(filter)) {
                filter = new HashMap<>();
                filterMap.put(requestUri, filter);
            }
        } else {
            filterMap = new HashMap<>();
            filter = new HashMap<>();
            filterMap.put(requestUri, filter);
            session.setAttribute("filter", filterMap);
        }

        if (StringUtils.equals("POST", method)) {
            filter.put("query", new HashMap<>());
            Enumeration params = servletRequest.getParameterNames();
//            Map<String, Object> conditions = new HashMap();
            while (params.hasMoreElements()) {
                String param = (String) params.nextElement();
                switch (param) {
                    case "pageNum":
                    case "numPerPage":
                    case "orderField":
                    case "orderDirection":
                        continue;
                }
                String value = servletRequest.getParameter(param);
                parseSimpleExpression(param, value);
            }
            Map<String, Object> conditions = (Map<String, Object>) filter.get("query");
            filterMap.put(requestUri, conditions);
            session.setAttribute("filter", filterMap);
        }
    }

    public static void main(String[] args) throws NoSuchFieldException, ClassNotFoundException {
        Map<String, Object> request = new HashMap<>();
        request.put("create[string][from]", "2019-12-12");
        request.put("create[string][to]", "2020-12-12");
        FilterSession filterSession = new FilterSession("orm.class");
        filterSession.testSuccess();
        System.out.println("filter = " + JsonUtils.toJson(filterSession.getFilter()));
    }

    public void testSuccess() throws NoSuchFieldException, ClassNotFoundException {
        filter = new HashMap<>();
        filter.put("query", new HashMap<>());
        Map<String, Object> request = new HashMap<>();
        request.put("create[string][from]", "2019-12-12");
        request.put("create[string][to]", "2020-12-12");
        request.put("id[int]", 1);
        Set<Map.Entry<String, Object>> entries = request.entrySet();
        for (Map.Entry entry : entries) {
            String key = (String) entry.getKey();
            Object value = entry.getValue();
            parseSimpleExpression(key, value.toString());
        }
    }

    private Map<String, Object> parseSimpleExpression(String param, String values) throws NoSuchFieldException, ClassNotFoundException {
        List<String> compareResult = parseSimpleExpressionCompare(param);
        param = compareResult.get(0);
        String compare = compareResult.get(1);
        List<String> caseResult = parseSimpleExpressionCast(param);
        param = caseResult.get(0);
        String cast = caseResult.get(1);
        innerParseSimpleExpression(param, values, compare, cast);
        return filter;
    }

    private void innerParseSimpleExpression(String param, String value, String compare, String cast) {
        switch (cast) {
            case "int":
            case "java.lang.Integer":
                if (StringUtils.equals("ge", compare)) {
                    saveFilterQuery(param, Integer.valueOf(value), compare);
                } else if (StringUtils.equals("le", compare)) {
                    saveFilterQuery(param, Integer.valueOf(value), compare);
                } else {
                    saveFilterQuery(param, Integer.valueOf(value));
                }
                break;
            case "long":
            case "java.lang.Long":
                if (StringUtils.equals("ge", compare)) {
                    saveFilterQuery(param, Long.valueOf(value), compare);
                } else if (StringUtils.equals("le", compare)) {
                    saveFilterQuery(param, Long.valueOf(value), compare);
                } else {
                    saveFilterQuery(param, Long.valueOf(value));
                }
                break;
            case "double":
            case "java.lang.Double":
                if (StringUtils.equals("ge", compare)) {
                    saveFilterQuery(param, Double.valueOf(value), compare);
                } else if (StringUtils.equals("le", compare)) {
                    saveFilterQuery(param, Double.valueOf(value), compare);
                } else {
                    saveFilterQuery(param, Double.valueOf(value));
                }
                break;
            default:
                if (compare.equals("like")) {
                    saveFilterQuery(param, value, "like");
                } else if (compare.equals("null")) {
                    saveFilterQuery(param, value, "null");
                } else if (compare.equals("!null")) {
                    saveFilterQuery(param, value, "not_null");
                } else if (compare.equals("true")) {
                    saveFilterQuery(param, value, "true");
                } else if (compare.equals("!true")) {
                    saveFilterQuery(param, value, "not_true");
                } else {
                    if (compare.equals("ge")) {
                        saveFilterQuery(param, value, "ge");
                    } else if (compare.equals("le")) {
                        saveFilterQuery(param, value, "le");
                    } else {
                        saveFilterQuery(param, value);
                    }
                }
                break;
        }
    }

    private void saveFilterQuery(String param, Object value) {
        saveFilterQuery(param, value, null);
    }

    private void saveFilterQuery(String param, Object value, String compare) {
        Map<String, Object> query = (Map<String, Object>) filter.get("query");
        if (StringUtils.isEmpty(compare)) {
            query.put(param, value);
        } else {
            Map<String, Object> innerQuery = new LinkedHashMap<>();
            if (query.containsKey(param)) {
                innerQuery = (Map<String, Object>) query.get(param);
            }
            innerQuery.put(compare, value);
            query.put(param, innerQuery);
        }
    }

    /**
     * 比较
     * @param param
     * @return
     */
    private List<String> parseSimpleExpressionCompare(String param) {
        String compare = "eq";
        if (param.endsWith("[from]")) {
            param = param.substring(0, param.indexOf("[from]"));
            compare = "ge";
        } else if (param.endsWith("[to]")) {
            param = param.substring(0, param.indexOf("[to]"));
            compare = "le";
        } else if (param.endsWith("[like]")) {
            param = param.substring(0, param.indexOf("[like]"));
            compare = "like";
        } else if (param.endsWith("[null]")) {
            param = param.substring(0, param.indexOf("[null]"));
            compare = "null";
        } else if (param.endsWith("[!null]")) {
            param = param.substring(0, param.indexOf("[!null]"));
            compare = "!null";
        } else if (param.endsWith("[true]")) {
            param = param.substring(0, param.indexOf("[true]"));
            compare = "true";
        } else if (param.endsWith("[!true]")) {
            param = param.substring(0, param.indexOf("[!true]"));
            compare = "!true";
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

    public Map<String, Object> getFilterMap() {
        return filterMap;
    }

    public void setFilterMap(Map<String, Object> filterMap) {
        this.filterMap = filterMap;
    }

    public Map<String, Object> getFilter() {
        return filter;
    }

    public void setFilter(Map<String, Object> filter) {
        this.filter = filter;
    }
}
