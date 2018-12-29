<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/12/29
  Time: 12:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="function" uri="http://github.com/tangtaiming/tags/ttm-functions" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/css.css">
</head>
<body>

<c:if test="${not empty msg}">
    <div class="message">${msg}</div>
</c:if>

<shiro:hasPermission name="role:create">
    <a href="${pageContext.request.contextPath}/role/create">角色新增</a><br/>
</shiro:hasPermission>
<table class="table">
    <thead>
    <tr>
        <th>角色名称</th>
        <th>角色描述</th>
        <th>拥有的资源</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${roleList}" var="role">
        <tr>
            <td>${role.role}</td>
            <td>${role.description}</td>
            <td>${function:resourceNames(role.resourceIds)}</td>
            <td>
                <shiro:hasPermission name="role:update">
                    <a href="${pageContext.request.contextPath}/role/${role.id}/update">修改</a>
                </shiro:hasPermission>

                <shiro:hasPermission name="role:delete">
                    <a href="${pageContext.request.contextPath}/role/${role.id}/delete">删除</a>
                </shiro:hasPermission>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
