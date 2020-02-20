<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
    String ctx = request.getContextPath();
    pageContext.setAttribute("ctx", ctx);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <title>个人博客系统</title>
    <link rel="shortcut icon" href="favicon.ico"/>
    <link rel="stylesheet" href="js/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="css/base.css">
    <link rel="stylesheet" href="css/lkblog.css">
    <link rel="stylesheet" href="css/blog_con.css">
    <link rel="stylesheet" href="${ctx }/css/pageStyle.css">
    <script src="${ctx}/js/jquery.min.js"></script>
    <script src="${ctx}/js/paging.js"></script>
</head>

<body>
<div id="top_bar" class="container hidden-xs hidden-sm">
    <div class="row">
        <div class="col-md-offset-1 col-md-7">
            <ul class="top-bar-left">
                <li style="margin-right: 20px;">
                    <a href="#" target="_blank">
                        <span class="icon-tel"></span>
                        喜欢我们,就点点关注吧!
                    </a>
                </li>
                <li>
                    <a href="#" target="_blank">
                        <span class="icon-email"></span>
                    </a>
                </li>
            </ul>
        </div>
        <div class="col-md-offset-3">
            <a><img src="${ctx}/images/QQ.png"></a>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <a><img src="${ctx}/images/wechat.png"></a>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <a><img src="${ctx}/images/web.png"></a>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <a data-align="left"><s:property value="#session.curUser.username"/> </a>

        </div>
    </div>
</div>
<!--导航-->
<nav class="navbar navbar-default navbar-lk">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>

        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

            <a class="navbar-brand" href="index.jsp" style="margin-left: 180px">
                <img src="${ctx}/images/wy_logo.jpg" alt="W YLogo" width="220">
            </a>

            <ul class="nav navbar-nav" id="nav" style="font-size: 20px">
                <li class="active"><a href="index.jsp">博客首页</a></li>
<%--                <li class="active"><a href="index.jsp">Java</a></li>--%>
<%--                <li class="active"><a href="index.jsp">Python</a></li>--%>
<%--                <li class="active"><a href="index.jsp">Web前端</a></li>--%>
            </ul>
            <a href="${ctx}/mgr_login.jsp" style="line-height:95px; height: 95px;font-size: 18px; color: #0a628f">
                发布文章
            </a>
        </div>
    </div>
</nav>
<!--banner-->
<section id="lk_blog_one">
    <img src="" alt="" class="one-img" width="280">
    <div class="one-right">
        <h1>W Y的个人网站</h1>
        <span>喜欢的话,就点点关注吧!</span>
        <div style="position: relative;" class="one-bottom">
            <button>关注我们</button>
            <img src="images/wy_QQ.jpg" alt="" width="100" class="one-ewm">
        </div>
    </div>
</section>
<script>
    $(function () {
        $.post("${pageContext.request.contextPath}/article_getCategory.action",{"parentid":0},function (data) {
            $(data).each(function (i,obj) {
                $("#nav").append("<li class=\"active\"><a href=\"index.jsp?parentid="+obj.cid+"\">"+obj.cname+"</a></li>")
            })
        },"json");
    });

</script>