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
    <form:form method="post" commandName="godformula" cssClass="pageForm required-validate"  onsubmit="return validateCallbackByDistributionDetails(this, dialogAjaxDone);">
        <form:hidden path="id"/>
        <div class="pageFormContent" layoutH="60">
            <textarea id="distributionDetails" style="width:100%;" name="distributionDetails" id="distributionDetails" class="textInput">${distributionDetails}</textarea>
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
<script src="/static/plugin/ckeditor/ckeditor.js" type="application/javascript"></script>
<script type="application/javascript">
    var ck = CKEDITOR.replace( 'distributionDetails' );
    validateCallbackByDistributionDetails = function (form, callback) {
        var $form = $(form);

        if (!$form.valid()) {
            return false;
        }

        var _submitFn = function () {
            $form.find(':focus').blur();
            //textarea 更新元素值
            ck.updateElement();
            var $data = $form.serializeArray();
            console.log('$data = ' + JSON.stringify($data));
            $.ajax({
                type: form.method || 'POST',
                url:$form.attr("action"),
                data:$data,
                dataType:"json",
                cache: false,
                success: callback || DWZ.ajaxDone,
                error: DWZ.ajaxError
            });
        }

        _submitFn();

        return false;
    }
</script>
