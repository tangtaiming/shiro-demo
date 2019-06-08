<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019-06-08
  Time: 0:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ul class="tree treeFolder">
    <c:forEach items="${m.list}" var="subm">
        <li>
            <a href="${subm.url}" target="navTab" rel="${subm.url}">${subm.name}</a>
        </li>
    </c:forEach>
</ul>
