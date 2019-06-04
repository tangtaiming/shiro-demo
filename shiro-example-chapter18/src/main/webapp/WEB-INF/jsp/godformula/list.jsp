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
<form id="pagerForm" method="post" action="/godformula">
    <input type="hidden" name="pageNum" value="${pageNum}" />
    <input type="hidden" name="numPerPage" value="${numPerPage}" />
    <input type="hidden" name="orderField" value="asc" />
</form>

<div class="pageContent" style="border-left:1px #B8D0D6 solid;border-right:1px #B8D0D6 solid">
    <div class="panelBar">
        <ul class="toolBar">
            <li><a class="delete" href="/godformula/{godformula_id}/delete" target="ajaxTodo" title="确定要删除吗？" warn="请选择一个数据" callback="navTabAjaxDone"><span>删除</span></a></li>
            <li><a class="edit" href="/godformula/{godformula_id}/updateDistributed"  target="dialog" rel="dlg_page1" minable="false" warn="请选择一个数据"><span>添加式神分布</span></a></li>
        </ul>
    </div>
    <div class="grid">
        <div id="w_list_print" layoutH="50">
            <table class="list" width="100%" targetType="navTab" asc="asc" desc="desc">
                <thead>
                <tr>
                    <th>网易编号</th>
                    <th>名称</th>
                    <th>缩小图</th>
                    <th>稀有度</th>
                    <th>分布详情</th>
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
                        <td>${godformula.distributionDetails}</td>
                        <td>${function:findNameByUserId(godformula.creator)}</td>
                        <td>${godformula.createDate}</td>
                        <td>
                            <div>
                                <shiro:hasPermission name="godformula:update">
                                    <a title="添加式神分布" href="/godformula/${godformula.id}/updateDistributed" target="dialog" class="btnEdit">添加式神分布</a>
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
    <c:import url="../basic/pages.jsp" />
</div>
<!--page unitBox end--->