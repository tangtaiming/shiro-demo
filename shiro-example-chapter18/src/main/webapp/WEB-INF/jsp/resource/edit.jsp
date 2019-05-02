<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/1/3
  Time: 23:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h2 class="contentTitle">${op}用户</h2>
<div class="pageContent">
    <form:form method="post" commandName="resource" cssClass="pageForm required-validate"  onsubmit="return validateCallback(this, navTabAjaxDone);">
        <form:hidden path="id"/>
        <form:hidden path="available"/>
        <form:hidden path="parentId"/>
        <form:hidden path="parentIds"/>
        <div class="pageFormContent nowrap" layoutH="98">
            <c:if test="${not empty parent}">
                <dl>
                    <dt><label>父节点名称：</label></dt>
                    <dd>
                        ${parent.name}
                        <span class="info"></span>
                    </dd>
                </dl>
            </c:if>

            <dl>
                <dt><form:label path="name"><c:if test="${not empty parent}">子</c:if>名称：</form:label></dt>
                <dd>
                    <form:input path="name"/>
                    <span class="info"></span>
                </dd>
            </dl>

            <dl>
                <dt><form:label path="type">类型：</form:label></dt>
                <dd>
                    <form:select path="type" items="${types}" itemLabel="info"/>
                    <span class="info"></span>
                </dd>
            </dl>

            <dl>
                <dt><form:label path="url">URL路径：</form:label></dt>
                <dd>
                    <form:input path="url"/>
                    <span class="info"></span>
                </dd>
            </dl>

            <dl>
                <dt><form:label path="permission">权限字符串：</form:label></dt>
                <dd>
                    <form:input path="permission"/>
                    <span class="info"></span>
                </dd>
            </dl>

        </div>
        <div class="formBar">
            <ul>
                <li><div class="buttonActive"><div class="buttonContent"><form:button>${op}</form:button></div></div></li>
                <li>
                    <div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
                </li>
            </ul>
        </div>
    </form:form>
</div>
