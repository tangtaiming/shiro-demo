<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/12/29
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="function" uri="http://github.com/tangtaiming/tags/ttm-functions" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/css.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/JQuery_zTree_v3.5.15/css/zTreeStyle/zTreeStyle.css">
<style>
    ul.ztree {margin-top: 10px;border: 1px solid #617775;background: #f0f6e4;width:220px;height:200px;overflow-y:scroll;overflow-x:auto;}
</style>
<h2 class="contentTitle">新增用户</h2>
<div id="pageContent" class="pageContent">
    <form:form method="post" commandName="role" cssClass="pageForm required-validate"  onsubmit="return validateCallback(this, navTabAjaxDone);">
        <div class="pageFormContent nowrap" layoutH="98">
            <dl>
                <dt><form:label path="role">角色名：</form:label></dt>
                <dd>
                    <form:input path="role"/>
                    <span class="info"></span>
                </dd>
            </dl>
            <dl>
                <dt><form:label path="description">角色描述：</form:label></dt>
                <dd>
                    <form:input path="description"/>
                    <span class="info"></span>
                </dd>
            </dl>
            <dl>
                <dt><form:label path="resourceIds">拥有的资源列表：</form:label></dt>
                <dd style="position:relative;">
                    <form:hidden path="resourceIds"/>
                    <input type="text" id="resourceName" name="resourceName" value="${function:resourceNames(role.resourceIds)}" readonly>
                    <a class="btnLook" id="menuBtn" href="#">选择</a>
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
    <div id="menuContent" class="menuContent" style="display:none; position: absolute;">
        <ul id="tree" class="ztree" style="margin-top:0; width:160px;"></ul>
    </div>
</div>
<script src="${pageContext.request.contextPath}/static/JQuery_zTree_v3.5.15/js/jquery.ztree.all-3.5.min.js"></script>
<script>
    $(function () {
        var setting = {
            check: {
                enable: true ,
                chkboxType: { "Y": "", "N": "" }
            },
            view: {
                dblClickExpand: false
            },
            data: {
                simpleData: {
                    enable: true
                }
            },
            callback: {
                onCheck: onCheck
            }
        };

        var zNodes =[
            <c:forEach items="${resourceList}" var="r">
            <c:if test="${not r.rootNode}">
            { id:${r.id}, pId:${r.parentId}, name:"${r.name}", checked:${function:in(role.resourceIds, r.id)}},
            </c:if>
            </c:forEach>
        ];

        function onCheck(e, treeId, treeNode) {
            var zTree = $.fn.zTree.getZTreeObj("tree"),
                nodes = zTree.getCheckedNodes(true),
                id = "",
                name = "";
            nodes.sort(function compare(a,b){return a.id-b.id;});
            for (var i=0, l=nodes.length; i<l; i++) {
                id += nodes[i].id + ",";
                name += nodes[i].name + ",";
            }
            if (id.length > 0 ) id = id.substring(0, id.length-1);
            if (name.length > 0 ) name = name.substring(0, name.length-1);
            $("#resourceIds").val(id);
            $("#resourceName").val(name);
//                hideMenu();
        }

        function showMenu() {
            var cityObj = $("#resourceName");
            var pageContentOffset = $("#pageContent").offset();
            var cityOffset = $("#resourceName").offset();
            $("#menuContent").css({left:(cityOffset.left - pageContentOffset.left) + "px", top:(cityOffset.top - pageContentOffset.top + cityObj.outerHeight()) + "px"}).slideDown("fast");
            //top:98px; left:135px;
            $("body").bind("mousedown", onBodyDown);
        }
        function hideMenu() {
            $("#menuContent").fadeOut("fast");
            $("body").unbind("mousedown", onBodyDown);
        }
        function onBodyDown(event) {
            if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
                hideMenu();
            }
        }

        $.fn.zTree.init($("#tree"), setting, zNodes);
        $("#menuBtn").click(showMenu);
    });
</script>
