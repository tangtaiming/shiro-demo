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
<form id="pagerForm" method="post" action="demo_page1.html">
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
                <li><a class="add" href="${pageContext.request.contextPath}/user/create" target="navTab" rel="user-create"><span>用户新增</span></a></li>
            </shiro:hasPermission>
            <li><a class="delete" href="demo/common/ajaxDone.html?uid={sid_user}" target="ajaxTodo" title="确定要删除吗？" warn="请选择一个用户"><span>删除</span></a></li>
            <li><a class="edit" href="demo_page4.html?uid={sid_user}" target="navTab" warn="请选择一个用户"><span>修改</span></a></li>
            <li class="line">line</li>
            <li><a class="icon" href="demo/common/dwz-team.xls" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
            <li><a class="icon" href="javascript:$.printBox('w_list_print')"><span>打印</span></a></li>
        </ul>
    </div>
    <div id="w_list_print">
        <table class="list" width="98%" targetType="navTab" asc="asc" desc="desc" layoutH="98">
            <thead>
                <tr>
                    <th>用户名</th>
                    <th>角色列表</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach items="${userList}" var="user" varStatus="status">
                <tr target="sid_user" rel="${status.index}">
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