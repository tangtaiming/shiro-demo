<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/12/28
  Time: 14:38
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

<shiro:hasPermission name="user:create">
    <a href="${pageContext.request.contextPath}/user/create">用户新增</a><br/>
</shiro:hasPermission>

<table class="table">
    <thead>
    <tr>
        <th>用户名</th>
        <th>角色列表</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${userList}" var="user">
        <tr>
            <td>${user.username}</td>
            <td>${function:roleNames(user.roleIds)}</td>
            <td>
                <shiro:hasPermission name="user:update">
                    <a href="${pageContext.request.contextPath}/user/${user.id}/update">修改</a>
                </shiro:hasPermission>

                <shiro:hasPermission name="user:delete">
                    <a href="${pageContext.request.contextPath}/user/${user.id}/delete">删除</a>
                </shiro:hasPermission>

                <shiro:hasPermission name="user:update">
                    <a href="${pageContext.request.contextPath}/user/${user.id}/changePassword">改密</a>
                </shiro:hasPermission>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
