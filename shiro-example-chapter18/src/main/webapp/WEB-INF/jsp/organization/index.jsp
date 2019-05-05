<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/1/7
  Time: 19:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="function" uri="http://github.com/tangtaiming/tags/ttm-functions" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/JQuery_zTree_v3.5.15/css/zTreeStyle/zTreeStyle.css">
<div class="pageContent" style="padding:5px">
    <div id="navTab2" class="tabs tabsPage">
        <div class="tabsHeader">
            <div class="tabsHeaderContent">
                <ul>
                    <li tabid="organization_1"><a href="javascript:;"><span>组织机构管理</span></a></li>
                </ul>
            </div>
        </div><!--tabsHeader end-->
        <div class="tabsContent">
            <div>
                <div layoutH="98" style="float:left; display:block; overflow:auto; width:240px; border:solid 1px #CCC; line-height:21px; background:#fff">
                    <ul id="ztree" class="ztree"></ul>
                </div>

                <div id="jbsxBox" class="unitBox" style="margin-left:246px;">
                    <!--#include virtual="list1.html" -->
                </div>
            </div>
        </div><!--tabsContent end-->
        <div class="tabsFooter">
            <div class="tabsFooterContent"></div>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/static/JQuery_zTree_v3.5.15/js/jquery.ztree.all-3.5.min.js"></script>
<script>
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
                beforeClick: doClick
            }
        };

        function doClick(treeId, treeNode, clickFlag) {
            var url = "/organization/" + treeNode.id + "/maintain";
            $('#jbsxBox').ajaxUrl({url:url});
        }

        var zNodes =[
            <c:forEach items="${organizationList}" var="o">
            {id:${o.id}, pId:${o.parentId}, name:"${o.name}", open:${o.rootNode}},
            </c:forEach>
        ];

        $.fn.zTree.init($("#ztree"), setting, zNodes);
    });
</script>
