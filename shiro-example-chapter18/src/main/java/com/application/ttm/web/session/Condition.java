package com.application.ttm.web.session;

import com.alibaba.druid.stat.TableStat;

/**
 * 条件
 * <p>@Author tangtaiming</p>
 * <p>@Date 2019-07-23</p>
 * <p>@Version 1.0</p>
 **/
public class Condition {

    //运算符
    private String operator;
    //属性
    private String property;
    //值
    private String value;

    private Condition(String operator, String property, String value) {
        this.operator = operator;
        this.property = property;
        this.value = value;
    }

    /**
     * 相等
     * @param property
     * @param value
     * @return
     */
    public static Condition eq(String property, String value) {
        return new Condition("$eq", property, value);
    }

    public static Condition gt(String property, String value) {
        return new Condition("$gt", property, value);
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
