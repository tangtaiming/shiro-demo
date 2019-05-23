<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019-05-21
  Time: 6:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="function" uri="http://github.com/tangtaiming/tags/ttm-functions" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<form id="pagerForm" method="post" action="/doubanMovie">
    <input type="hidden" name="status" value="${param.status}">
    <input type="hidden" name="keywords" value="${param.keywords}" />
    <input type="hidden" name="pageNum" value="${pageNum}" />
    <input type="hidden" name="numPerPage" value="${numPerPage}" />
    <input type="hidden" name="orderField" value="asc" />
</form>

<c:if test="${not empty msg}">
    <div class="message">${msg}</div>
</c:if>

<div class="pageContent">
    <div class="panelBar">
        <ul class="toolBar">
            <shiro:hasPermission name="doubanMovie:create">
                <li><a class="add" href="${pageContext.request.contextPath}/doubanMovie/create" target="navTab" rel="doubanMovie-create"><span>电影新增</span></a></li>
            </shiro:hasPermission>
            <li><a class="delete" href="/doubanMovie/{id}/delete" target="ajaxTodo" title="确定要删除吗？" warn="请选择一个电影" callback="navTabAjaxDone"><span>删除</span></a></li>
            <li><a class="edit" href="/doubanMovie/{id}/update" target="navTab" warn="请选择一个电影"><span>修改</span></a></li>
        </ul>
    </div>
    <div id="w_list_print" layoutH="50">
        <table class="list" width="100%" targetType="navTab" asc="asc" desc="desc">
            <thead>
            <tr>
                <th width="100">豆瓣标识</th>
                <th width="150">标题</th>
                <th width="400">原标题</th>
                <th>图片</th>
                <th width="50">年</th>
                <th width="50">星级</th>
                <th width="50">平均分</th>
                <th width="50">热映</th>
                <th width="50">TOP250</th>
                <th>创建人</th>
                <th>创建时间</th>
                <th width="100">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${doubanMovies}" var="movie">
                <tr target="id" rel="${movie.id}">
                    <td>${movie.doubenIntheatersId}</td>
                    <td>${movie.title}</td>
                    <td>${movie.originalTitle}</td>
                    <td>
                       <img height="200" width="124" src="${movie.smallImage}">
                    </td>
                    <td>${movie.year}</td>
                    <td>${movie.stars}</td>
                    <td>${movie.average}</td>
                    <td>${movie.intheaters}</td>
                    <td>${movie.top250}</td>
                    <td>${function:findNameByUserId(movie.creator)}</td>
                    <td>${movie.createDate}</td>
                    <td>
                        <div>
                            <shiro:hasPermission name="role:delete">
                                <a href="${pageContext.request.contextPath}/doubanMovie/${role.id}/delete" target="ajaxTodo" title="确定要删除吗？" class="btnDel">删除</a>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="user:update">
                                <a title="修改"  href="${pageContext.request.contextPath}/doubanMovie/${role.id}/update" target="navTab" class="btnEdit">修改</a>
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
</div>
<!--page unitBox end--->
