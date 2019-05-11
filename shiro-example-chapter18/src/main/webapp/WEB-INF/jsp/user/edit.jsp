<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/12/28
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@taglib prefix="zhangfn" uri="http://github.com/zhangkaitao/tags/zhang-functions" %>--%>
<h2 class="contentTitle">${op}用户</h2>
<div class="pageContent">
    <form:form method="post" commandName="user" cssClass="pageForm required-validate"  onsubmit="return validateCallback(this, navTabAjaxDone);">
        <form:hidden path="id"/>
        <form:hidden path="salt"/>
        <form:hidden path="locked"/>
        <c:if test="${op ne '新增'}">
            <form:hidden path="password"/>
        </c:if>
        <div class="pageFormContent nowrap" layoutH="98">
            <dl>
                <dt><form:label path="username">登陆名：</form:label></dt>
                <dd>
                    <form:input path="username"/>
                    <span class="info"></span>
                </dd>
            </dl>
            <dl>
                <dt><form:label path="name">用户名：</form:label></dt>
                <dd>
                    <form:input path="name"/>
                    <span class="info"></span>
                </dd>
            </dl>
            <c:if test="${op eq '新增'}">
                <dl>
                    <dt><form:label path="password">密码：</form:label></dt>
                    <dd>
                        <form:password path="password"/>
                        <span class="info"></span>
                    </dd>

                </dl>
            </c:if>
            <dl>
                <dt><form:label path="roleIds">角色列表：</form:label></dt>
                <dd>
                    <form:select path="roleIds" items="${roleList}" itemLabel="description" itemValue="id" multiple="true"/>
                    (按住shift键多选)
                    <span class="info"></span>
                </dd>
            </dl>
        </div>
        <div class="formBar">
            <ul>
                <!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
                <li><div class="buttonActive"><div class="buttonContent"><form:button>${op}</form:button></div></div></li>
                <li>
                    <div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
                </li>
            </ul>
        </div>
    </form:form>
</div>
