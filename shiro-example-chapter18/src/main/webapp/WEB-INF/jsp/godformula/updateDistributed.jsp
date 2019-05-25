<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019-05-24
  Time: 22:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@taglib prefix="zhangfn" uri="http://github.com/zhangkaitao/tags/zhang-functions" %>--%>
<div class="pageContent">
    <form:form method="post" commandName="godformula" cssClass="pageForm required-validate"  onsubmit="return validateCallback(this, dialogAjaxDone);">
        <form:hidden path="id"/>
        <div class="pageFormContent" layoutH="60">
            <p>
                <form:label path="distributionDetails">分布详情：</form:label>
                <textarea name="distributionDetails" id="distributionDetails" class="textInput"></textarea>
            </p>
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
