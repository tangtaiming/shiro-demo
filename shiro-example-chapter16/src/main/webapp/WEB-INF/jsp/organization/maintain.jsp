<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/1/7
  Time: 19:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<form:form id="form" method="post" commandName="organization">
    <form:hidden path="id"/>
    <form:hidden path="available"/>
    <form:hidden path="parentId"/>
    <form:hidden path="parentIds"/>

    <div class="form-group">
        <form:label path="name">名称：</form:label>
        <form:input path="name"/>
    </div>
    <shiro:hasPermission name="organization:update">
        <form:button id="updateBtn">修改</form:button>
    </shiro:hasPermission>

    <shiro:hasPermission name="organization:delete">
        <c:if test="${not organization.rootNode}">
            <form:button id="deleteBtn">删除</form:button>
        </c:if>
    </shiro:hasPermission>

    <shiro:hasPermission name="organization:create">
        <form:button id="appendChildBtn">添加子节点</form:button>
    </shiro:hasPermission>

    <shiro:hasPermission name="organization:update">
        <c:if test="${not organization.rootNode}">
            <form:button id="moveBtn">移动节点</form:button>
        </c:if>
    </shiro:hasPermission>
</form:form>

