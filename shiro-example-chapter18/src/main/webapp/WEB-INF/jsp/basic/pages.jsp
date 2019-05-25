<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019-05-24
  Time: 21:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="panelBar" >
    <div class="pages">
        <span>显示</span>
        <select name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
            <option <c:if test="${20 == numPerPage}">selected="selected"</c:if> value="20">20</option>
            <option <c:if test="${50 == numPerPage}">selected="selected"</c:if> value="50">50</option>
            <option <c:if test="${100 == numPerPage}">selected="selected"</c:if> value="100">100</option>
            <option <c:if test="${200 == numPerPage}">selected="selected"</c:if> value="200">200</option>
        </select>
        <span>条，共${totalCount}条</span>
    </div>
    <div class="pagination" targetType="navTab" totalCount="${totalCount}" numPerPage="${numPerPage}" pageNumShown="5" currentPage="${pageNum}"></div>
</div>
<!--table pager end--->