<%--
  Created by IntelliJ IDEA.
  User: xiantao
  Date: 2019/5/7
  Time: 6:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="function" uri="http://github.com/tangtaiming/tags/ttm-functions" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<form id="pagerForm" method="post" action="/doubanIntheaters">
    <input type="hidden" name="status" value="${param.status}">
    <input type="hidden" name="keywords" value="${param.keywords}" />
    <input type="hidden" name="pageNum" value="1" />
    <input type="hidden" name="numPerPage" value="20" />
    <input type="hidden" name="orderField" value="asc" />
</form>

<c:if test="${not empty msg}">
    <div class="message">${msg}</div>
</c:if>

<div class="pageContent" style="border-left:1px #B8D0D6 solid;border-right:1px #B8D0D6 solid">
    <div class="panelBar">
        <ul class="toolBar">
            <shiro:hasPermission name="doubanIntheaters:create">
                <li><a class="add" href="${pageContext.request.contextPath}/doubanIntheaters/create" target="navTab" rel="doubanIntheaters-create"><span>电影新增</span></a></li>
            </shiro:hasPermission>
            <li><a class="delete" href="/doubanIntheaters/{doubanIntheaters_id}/delete" target="ajaxTodo" title="确定要删除吗？" warn="请选择一条数据" callback="navTabAjaxDone"><span>删除</span></a></li>
            <li><a class="edit" href="/doubanIntheaters/{doubanIntheaters_id}/update" target="navTab" warn="请选择一条数据"><span>修改</span></a></li>
        </ul>
    </div>
    <div class="grid">
        <div id="w_list_print">
            <table class="list" width="100%" targetType="navTab" asc="asc" desc="desc" layoutH="98">
                <thead>
                <tr>
                    <th>豆瓣标识</th>
                    <th>标题</th>
                    <th>图片</th>
                    <th>年</th>
                    <th>星级</th>
                    <th>平均值</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${doubanIntheaters}" var="row" varStatus="status">
                    <tr target="doubanIntheaters_id" rel="${row.id}">
                        <td>${row.doubenIntheatersId}</td>
                        <td>${row.title}</td>
                        <td>
                            <img height="200" width="124" src="${function:doubanIntheatersReplaceImage(row.smallImage)}">
                        </td>
                        <td>${row.year}</td>
                        <td>${row.stars}</td>
                        <td>${row.average}</td>
                        <td>
                            <div>
                                <shiro:hasPermission name="doubanIntheaters:delete">
                                    <a title="删除" href="${pageContext.request.contextPath}/doubanIntheaters/${row.id}/delete" target="ajaxTodo" title="确定要删除吗？" class="btnDel">删除</a>
                                </shiro:hasPermission>
                                <shiro:hasPermission name="doubanIntheaters:update">
                                    <a title="修改" href="${pageContext.request.contextPath}/doubanIntheaters/${row.id}/update" target="navTab" class="btnEdit">修改</a>
                                </shiro:hasPermission>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
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
