<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/1/7
  Time: 19:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<div class="pageContent" style="border-left:1px #B8D0D6 solid;border-right:1px #B8D0D6 solid; border-bottom: 1px #B8D0D6 solid">
    <div class="panelBar">
        <ul class="toolBar">
            <li><a class="edit" href="/organization/${organization.id}/update" target="dialog" mask="true"><span>修改</span></a></li>
            <li><a class="delete" href="/organization/${organization.id}/delete" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
            <li><a class="add" href="/organization/${organization.id}/appendChild" target="dialog" mask="true"><span>添加子节点</span></a></li>
            <li><a class="add" href="/organization/${organization.id}/move" target="dialog" mask="true"><span>移动子节点</span></a></li>
        </ul>
    </div>
    <form:form method="post" commandName="organization" cssClass="pageForm required-validate"  onsubmit="return validateCallback(this, navTabAjaxDone);">
        <form:hidden path="id" />
        <div class="pageFormContent nowrap">
            <dl>
                <dt><form:label path="name">名称：</form:label></dt>
                <dd>
                    <form:input path="name"/>
                    <span class="info"></span>
                </dd>
            </dl>
        </div>
    </form:form>
</div>
