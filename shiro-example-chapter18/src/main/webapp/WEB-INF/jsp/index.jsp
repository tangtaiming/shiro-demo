<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/12/27
  Time: 14:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>TTM</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/layout-default-latest.css">
</head>
<body>

<iframe name="content" class="ui-layout-center"
        src="${pageContext.request.contextPath}/welcome" frameborder="0" scrolling="auto"></iframe>
<div class="ui-layout-north">欢迎[<shiro:principal/>]，<a href="${pageContext.request.contextPath}/logout">退出</a></div>
<div class="ui-layout-south">
    Copyright © 2008-2019 TTM xxx.com 版权所有
</div>
<div class="ui-layout-west">
    功能菜单<br/>
    <c:forEach items="${menus}" var="m">
        <a href="${m.url}" target="content">${m.name}</a><br/>
    </c:forEach>
</div>


<script src="${pageContext.request.contextPath}/static/js/jquery-1.11.0.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery.layout-latest.min.js"></script>
<script>
    $(function () {
        $(document).ready(function () {
            $('body').layout({ applyDemoStyles: true });
        });
    });
</script>
</body>
</html>
