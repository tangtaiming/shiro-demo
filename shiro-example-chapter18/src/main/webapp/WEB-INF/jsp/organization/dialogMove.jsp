<%--
  Created by IntelliJ IDEA.
  User: xiantao
  Date: 2019/5/5
  Time: 14:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/JQuery_zTree_v3.5.15/css/zTreeStyle/zTreeStyle.css">
<style>
    ul.ztreeCustom {margin-top: 10px;border: 1px solid #617775;background: #f0f6e4;width:240px;height:200px;overflow-y:scroll;overflow-x:auto;}
</style>
<div id="dialogMovePageContent" class="pageContent">
    <form:form method="post" commandName="source" cssClass="pageForm required-validate"  onsubmit="return validateCallback(this, dialogAjaxDone);">
        <div class="pageFormContent" layoutH="58">
            <div class="unit">
                <label>源节点名称：</label>
                    ${source.name}
            </div>
            <div class="unit">
                <label>目标节点名称：</label>
                <input type="text" id="targetName" readonly="true" />
                <input type="hidden" id="targetId" name="targetId" />
                <a class="btnLook" id="menuBtn" href="#">选择</a>
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
<div id="menuContent" class="menuContent" style="display:none; position: absolute;">
    <ul id="tree_1" class="ztree ztreeCustom" style="margin-top:0; width:160px;"></ul>
</div>
<script src="${pageContext.request.contextPath}/static/JQuery_zTree_v3.5.15/js/jquery.ztree.all-3.5.min.js"></script>
<script type="application/javascript">
    $(function () {
        var setting = {
            view: {
                dblClickExpand: false
            },
            data: {
                simpleData: {
                    enable: true
                }
            },
            callback: {
                onClick: onClick
            }
        };

        var zNodes =[
            <c:forEach items="${targetList}" var="o">
            { id:${o.id}, pId:${o.parentId}, name:"${o.name}", open:${o.rootNode}},
            </c:forEach>
        ];

        function onClick(e, treeId, treeNode) {
            var zTree = $.fn.zTree.getZTreeObj("tree_1"),
                nodes = zTree.getSelectedNodes(),
                id = "",
                name = "";
            nodes.sort(function compare(a,b){return a.id-b.id;});
            for (var i=0, l=nodes.length; i<l; i++) {
                id += nodes[i].id + ",";
                name += nodes[i].name + ",";
            }
            if (id.length > 0 ) id = id.substring(0, id.length-1);
            if (name.length > 0 ) name = name.substring(0, name.length-1);
            $("#targetId").val(id);
            $("#targetName").val(name);
            hideMenu();
        }

        function showMenu() {
            var cityObj = $("#targetName");
            var pageContentOffset = $(".dialog").offset();
            var cityOffset = $("#targetName").offset();
            $("#menuContent").css({left:(cityOffset.left - pageContentOffset.left) + "px", top:(cityOffset.top - pageContentOffset.top + cityObj.outerHeight()) + "px"}).slideDown("fast");

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

        $.fn.zTree.init($("#tree_1"), setting, zNodes);
        $("#menuBtn").click(showMenu);
    });
</script>