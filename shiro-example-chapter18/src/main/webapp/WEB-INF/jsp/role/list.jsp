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
<form id="pagerForm" method="post" action="/role">
    <input type="hidden" name="status" value="${param.status}">
    <input type="hidden" name="keywords" value="${param.keywords}" />
    <input type="hidden" name="pageNum" value="1" />
    <input type="hidden" name="numPerPage" value="20" />
    <input type="hidden" name="orderField" value="asc" />
</form>

<c:if test="${not empty msg}">
    <div class="message">${msg}</div>
</c:if>

<div class="pageContent">
    <div class="panelBar">
        <ul class="toolBar">
            <shiro:hasPermission name="user:create">
                <li><a class="add" href="${pageContext.request.contextPath}/role/create" target="navTab" rel="role-create"><span>角色新增</span></a></li>
            </shiro:hasPermission>
            <li><a class="delete" href="/role/{id}/delete" target="ajaxTodo" title="确定要删除吗？" warn="请选择一个角色" callback="navTabAjaxDone"><span>删除</span></a></li>
            <li><a class="edit" href="/role/{id}/update" target="navTab" warn="请选择一个角色"><span>修改</span></a></li>
        </ul>
    </div>
    <div id="w_list_print">
        <table class="list" width="100%" targetType="navTab" asc="asc" desc="desc" layoutH="98">
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
                <tr target="id" rel="${role.id}">
                    <td>${role.role}</td>
                    <td>${role.description}</td>
                    <td>${function:resourceNames(role.resourceIds)}</td>
                    <td>
                        <div>
                            <shiro:hasPermission name="role:delete">
                                <a href="${pageContext.request.contextPath}/role/${role.id}/delete" target="ajaxTodo" title="确定要删除吗？" class="btnDel">删除</a>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="user:update">
                                <a title="修改"  href="${pageContext.request.contextPath}/role/${role.id}/update" target="navTab" class="btnEdit">修改</a>
                            </shiro:hasPermission>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <!--table data end--->
    <div class="panelBar" >
        <div class="pages">
            <span>显示</span>
            <select name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
                <option value="20">20</option>
                <option value="50">50</option>
                <option value="100">100</option>
                <option value="200">200</option>
            </select>
            <span>条，共23条</span>
        </div>
        <div class="pagination" targetType="navTab" totalCount="200" numPerPage="20" pageNumShown="10" currentPage="1"></div>
    </div>
    <!--table pager end--->
</div>
<!--page unitBox end--->
