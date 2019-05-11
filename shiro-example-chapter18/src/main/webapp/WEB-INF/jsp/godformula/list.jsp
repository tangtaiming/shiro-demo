<%--
  Created by IntelliJ IDEA.
  User: xiantao
  Date: 2019/5/10
  Time: 23:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="function" uri="http://github.com/tangtaiming/tags/ttm-functions" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<form id="pagerForm" method="post" action="/user">
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
            <shiro:hasPermission name="user:create">
                <li><a class="add" href="/godformula/create" target="navTab" rel="godformula-create"><span>用户新增</span></a></li>
            </shiro:hasPermission>
            <li><a class="delete" href="/godformula/{godformula_id}/delete" target="ajaxTodo" title="确定要删除吗？" warn="请选择一个数据" callback="navTabAjaxDone"><span>删除</span></a></li>
            <li><a class="edit" href="/godformula/{godformula_id}/update" target="navTab" warn="请选择一个数据"><span>修改</span></a></li>
        </ul>
    </div>
    <div class="grid">
        <div id="w_list_print">
            <table class="list" width="100%" targetType="navTab" asc="asc" desc="desc" layoutH="98">
                <thead>
                <tr>
                    <th>网易编号</th>
                    <th>名称</th>
                    <th>缩小图</th>
                    <th>稀有度</th>
                    <th>创建人</th>
                    <th>创建时间</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${godformulaList}" var="godformula" varStatus="status">
                    <tr target="godformula_id" rel="${godformula.id}">
                        <td>${godformula.godformulaId}</td>
                        <td>${godformula.name}</td>
                        <td>
                            <img src="${godformula.image}" width="120" height="120">
                        </td>
                        <td>${function:rarityName(godformula.rarity)}</td>
                        <td>${function:findNameByUserId(godformula.creator)}</td>
                        <td>${godformula.createDate}</td>
                        <td>
                            <div>
                                <shiro:hasPermission name="godformula:delete">
                                    <a title="删除" href="/godformula/${godformula.id}/delete" target="ajaxTodo" title="确定要删除吗？" class="btnDel">删除</a>
                                </shiro:hasPermission>
                                <shiro:hasPermission name="godformula:update">
                                    <a title="修改" href="/user/${godformula.id}/update" target="navTab" class="btnEdit">修改</a>
                                </shiro:hasPermission>
                                <shiro:hasPermission name="godformula:update">
                                    <a title="修改密码" href="/godformula/${godformula.id}/changePassword" target="navTab">
                                        改密
                                    </a>
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