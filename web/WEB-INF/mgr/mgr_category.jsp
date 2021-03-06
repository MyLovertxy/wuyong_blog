<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
    String ctx = request.getContextPath();
    pageContext.setAttribute("ctx", ctx);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="${ctx}/css/style.css" type="text/css" />
    <link rel="stylesheet" href="${ctx}/css/amazeui.min.css" />
    <link rel="stylesheet" href="js/pageStyle.css">
    <script src="${ctx}/js/jquery.min.js"></script>
    <style>
        #modal_content2{
            padding: 30px 20px;
            width: 400px;
            height: 200px;
            background: white;
            position: fixed;
            left: 50%;
            top: 50%;
            margin-left: -200px;
            margin-top: -100px;
            display: none;
        }
        #close2{
            position: absolute;
            right: 10px;
            top: 10px;
        }
    </style>
</head>
<body>


<div class="main_top">
    <div class="am-cf am-padding am-padding-bottom-0">
        <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">分类管理</strong><small></small></div>
    </div>
    <hr>
    <div class="am-g">
        <div class="am-u-sm-12 am-u-md-6">
            <div class="am-btn-toolbar">
                <div class="am-btn-group am-btn-group-xs">
                    <button id="add" class="am-btn am-btn-default">
                        <span class="am-icon-plus"></span> 添加分类</button>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="goods_list" id="account_List">
    <ul class="title_ul">
        <li>parentid</li>
        <li>分类名称</li>
        <li>修改分类</li>
        <li>删除分类</li>
    </ul>
<%--取数据--%>
    <s:iterator value="categorylist" var="category">
        <ul class="list_goods_ul">
            <li><s:property value="#category.parentid"/> </li>
            <li><s:property value="#category.cname"/></li>
            <li>
                <a href="#" class="updatebtn" data-id="<s:property value="#category.cid"/>">
                    <img class="img_icon" src="${ctx}/images/edit_icon.png" alt="修改">
                </a>
            </li>
            <li><a href="${pageContext.request.contextPath}/category_delete.action?cid=<s:property value="#category.cid"/>">
                <img class="img_icon" src="${ctx}/images/delete_icon.png" alt="删除"></a></li>
        </ul>
    </s:iterator>

</div>

<div id="modal_view">


</div>

<div id="modal_content">
    <div id="close"><img src="${ctx}/images/delete_icon.png" alt="关闭"></div>
    <div class="edit_content">

<%--        <div class="item1">--%>
<%--            <div>--%>
<%--                <span>添加分类：</span>--%>
<%--            </div>--%>
<%--        </div>--%>
        <div class="item1">
            <div>
                <span>parentid：</span>
                <input type="text" class="am-form-field" id="parentid">&nbsp;&nbsp;
                <br/>
                <span>分类名称：</span>
                <input type="text" class="am-form-field" id="cname">&nbsp;&nbsp;
                <br/>
                <button class="am-btn am-btn-default" type="button" id="addcategory">添加</button>
            </div>
        </div>

    </div>
</div>
<div id="modal_content2" style="height: 250px; display: none">
<div id="close2"><img src="${ctx}/images/delete_icon.png" alt=""></div>
<div class="edit_content">
            <div class="item1">
                <div>
                    <span>修改分类：</span>
                </div>
            </div>
    <div class="item1" >
        <div>
                <span>parentid：</span>
                <input type="text" class="am-form-field" id="parentid2" >&nbsp;&nbsp;
                <br/>
                <span>分类名称：</span>
                <input type="text" class="am-form-field" id="cname2" >&nbsp;&nbsp;
                <br/>
                <input type="hidden" id="cid2">
                <button class="am-btn am-btn-default" type="button" id="updatebtn">修改</button>
        </div>
        </div>
    </div>
</div>
<script>
    $(function () {
        $('#add').click(function () {
            $("#modal_view").fadeIn();
            $("#modal_content").fadeIn();
        });

        $("#close").click(function () {
            $("#modal_view").fadeOut();
            $("#modal_content").fadeOut();
        });
        //监听添加按钮点击
        $("#addcategory").click(function () {
            //获取文本框内容
            var parentid=$("#parentid").val();
            var cname=$("#cname").val();
            //发送请求
            $(window).attr('location','${pageContext.request.contextPath}/category_add.action?parentid='+parentid+'&cname='+cname);
        });
        //监听修改按钮点击
        $(".updatebtn").click(function () {
            //获取绑定的id
            var cid=$(this).data("id");
            //发送异步请求，jQuery封装ajax
            $.post("${pageContext.request.contextPath}/category_updateUI.action",{"cid":cid},function (data) {
                    console.log("-------");
                    console.log(data);
                    $("#parentid2").val(data[0].parentid);
                    $("#cname2").val(data[0].cname);
                    $("#cid2").val(data[0].cid);
            },"json");
            //弹出修改页面
            $("#modal_view").fadeIn();
            $("#modal_content2").fadeIn();
        });
        $("#close2").click(function () {
            $("#modal_view").fadeOut();
            $("#modal_content2").fadeOut();
        });
        //修改
        $("#updatebtn").click(function () {
            //获取值
           var parentid2=$("#parentid2").val();
            var cname2=$("#cname2").val();
            var cid2=$("#cid2").val();
            $(window).attr('location','${pageContext.request.contextPath}/category_update.action?parentid='+parentid2+'&cname='+cname2+'&cid='+cid2);
        });
    });
</script>
</body>
</html>