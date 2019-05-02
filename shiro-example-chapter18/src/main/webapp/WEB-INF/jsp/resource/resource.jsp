<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/12/30
  Time: 15:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="function" uri="http://github.com/tangtaiming/tags/ttm-functions" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/css.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/jquery-treetable/stylesheets/jquery.treetable.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/jquery-treetable/stylesheets/jquery.treetable.theme.default.css">

<form id="pagerForm" method="post" action="/user">
    <input type="hidden" name="status" value="${param.status}">
    <input type="hidden" name="keywords" value="${param.keywords}" />
    <input type="hidden" name="pageNum" value="1" />
    <input type="hidden" name="numPerPage" value="20" />
    <input type="hidden" name="orderField" value="asc" />
</form>

<div class="pageContent" style="border-left:1px #B8D0D6 solid;border-right:1px #B8D0D6 solid">
    <div class="panelBar">
        <ul class="toolBar">
            <shiro:hasPermission name="resource:create">
                <li><a class="add" href="${pageContext.request.contextPath}/resource/{resource_id}/appendChild" target="navTab" warn="请选择一个资源" rel="resource-create"><span>资源新增</span></a></li>
            </shiro:hasPermission>
            <li><a class="delete" href="/resource/{resource_id}/delete" target="ajaxTodo" title="确定要删除吗？" warn="请选择一个资源" callback="navTabAjaxDone"><span>删除</span></a></li>
            <li><a class="edit" href="/resource/{resource_id}/update" target="navTab" warn="请选择一个资源"><span>修改</span></a></li>
        </ul>
    </div>
    <div class="grid">
        <div id="w_list_print">
            <table id="table" class="list" width="100%" targetType="navTab" asc="asc" desc="desc" layoutH="98">
                <thead>
                <tr>
                    <th>名称</th>
                    <th>类型</th>
                    <th>URL路径</th>
                    <th>权限字符串</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${resourceList}" var="resource">
                    <tr target="resource_id" rel="${resource.id}" data-tt-id='${resource.id}' <c:if test="${not resource.rootNode}">data-tt-parent-id='${resource.parentId}'</c:if>>
                        <td>${resource.name}</td>
                        <td>${resource.type.info}</td>
                        <td>${resource.url}</td>
                        <td>${resource.permission}</td>
                        <td>
                            <div>
                                <c:if test="${not resource.rootNode}">
                                    <shiro:hasPermission name="resource:delete">
                                        <a class="deleteBtn" href="/resource/${resource.id}/delete" data-id="${resource.id}" target="ajaxTodo" title="确定要删除吗？">删除</a>
                                    </shiro:hasPermission>
                                </c:if>

                                <shiro:hasPermission name="resource:create">
                                    <c:if test="${resource.type ne 'button'}">
                                        <a href="${pageContext.request.contextPath}/resource/${resource.id}/appendChild" target="navTab">添加子节点</a>
                                    </c:if>
                                </shiro:hasPermission>

                                <shiro:hasPermission name="resource:update">
                                    <a href="${pageContext.request.contextPath}/resource/${resource.id}/update" target="navTab">修改</a>
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

<script src="${pageContext.request.contextPath}/static/jquery-treetable/javascripts/src/jquery.treetable.js"></script>
<script>
    $(function() {
        $("#table").treetable({ expandable: true }).treetable("expandNode", 1);
        $(".deleteBtn").click(function() {
            if(confirm("确认删除吗?")) {
                location.href = "${pageContext.request.contextPath}/resource/"+$(this).data("id")+"/delete";
            }
        });
    });
</script>