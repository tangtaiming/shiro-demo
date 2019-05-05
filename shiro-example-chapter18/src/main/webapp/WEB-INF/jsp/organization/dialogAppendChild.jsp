<%--
  Created by IntelliJ IDEA.
  User: xiantao
  Date: 2019/5/5
  Time: 4:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<div class="pageContent">
    <form:form method="post" commandName="child" cssClass="pageForm required-validate"  onsubmit="return validateCallback(this, dialogAjaxDone);">
        <form:hidden path="id"/>
        <form:hidden path="parentId"/>
        <form:hidden path="parentIds"/>
        <div class="pageFormContent" layoutH="58">
            <div class="unit">
                <label>父节点名称：</label>
                ${parent.name}
            </div>
            <div class="unit">
                <form:label path="name">子节点名称：</form:label>
                <form:input path="name" size="30" class="required"/>
            </div>
        </div>
        <div class="formBar">
            <ul>
                <li><div class="buttonActive"><div class="buttonContent"><button type="submit">${op}</button></div></div></li>
                <li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
            </ul>
        </div>
    </form:form>
</div>
