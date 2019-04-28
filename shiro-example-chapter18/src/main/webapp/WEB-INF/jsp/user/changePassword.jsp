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
<h2 class="contentTitle">修改密码</h2>
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
                <dt><label for="newPassword">新密码：</label></dt>
                <dd>
                    <input type="text" id="newPassword" name="newPassword"/>
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

