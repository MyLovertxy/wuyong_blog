<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="S" uri="/struts-tags" %>
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
        #modal_content_edit{
            padding: 30px 20px;
            width: 400px;
            height: 250px;
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
        <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">管理员列表</strong><small></small></div>
    </div>
    <hr>
    <div class="am-g">
        <div class="am-u-sm-12 am-u-md-6">
            <div class="am-btn-toolbar">
                <div class="am-btn-group am-btn-group-xs">
                    <button id="add" class="am-btn am-btn-default">
                        <span class="am-icon-plus"></span> 添加管理员</button>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="goods_list" id="account_List">
    <ul class="title_ul">
        <li>序号</li>
        <li>用户</li>
        <li>修改密码</li>
        <li>移除管理员</li>
    </ul>
    <s:iterator value="userLsit" var="user">
    <ul class="list_goods_ul">
        <li><s:property value="#user.id"/> </li>
        <li><s:property value="#user.username"/></li>
        <li><a href="#"><img class="img_icon" src="${ctx}/images/edit_icon.png" alt="修改" data-id="<s:property value="#user.id"/>"
          data-username="<s:property value="#user.username"/>" data-password="<s:property value="#user.password"/>"></a></li>
        <li><a href="${ctx}/account_delete.action?id=<s:property value="#user.id"/> "><img class="img_icon" src="${ctx}/images/delete_icon.png" alt="删除"></a></li>
    </ul>
    </s:iterator>
</div>

    <div id="modal_view">


    </div>

<div id="modal_content_account">
    <div id="close"><img src="${ctx}/images/delete_icon.png" alt="取消"></div>
    <div class="edit_content">
        <form id="add_form" action="${ctx}/account_add.action" method="post">
        <div class="item1">
            <div>
                <span>添加管理员：</span>
            </div>
        </div>
        <div class="item1">
            <div>
                <span>用户名：</span>
                <input type="text" name="username" class="am-form-field" >&nbsp;&nbsp;
            </div>

        </div>
        <div class="item1">
            <div>
                <span>密    码：</span>
                <input type="text" name="password" class="am-form-field" >&nbsp;&nbsp;
            </div>

        </div>
        <div class="item1">
            <button class="am-btn am-btn-default" type="button" id="add_btn">添加</button>
        </div>
        </form>
    </div>
</div>
<div id="modal_content_edit">
    <div id="close2"><img src="${ctx}/images/delete_icon.png" alt="取消"></div>
    <div class="edit_content">
        <form id="edit_form" action="${ctx}/account_add.action" method="post">
            <div class="item1">
                <div>
                    <span>旧密码：</span>
                    <input type="password" name="pre_password" id="pre_password" class="am-form-field">
                    <br/>
                    <span>新密码：</span>
                    <input type="password" name="password" id="password" class="am-form-field" disabled="disabled">
                    <br/>
                    <span>确认密码：</span>
                    <input type="password" name="password2" id="password2" class="am-form-field" disabled="disabled">
                    <br/>
                    <button class="am-btn am-btn-default" type="button" id="edit_btn" disabled="disabled">修改</button>
                </div>
            </div>
        </form>
    </div>
</div>
<script>
    $(function () {
        //添加
        $('#add').click(function () {
            $("#modal_view").fadeIn();
            $("#modal_content_account").fadeIn();
        });

        $("#close").click(function () {
            $("#modal_view").fadeOut();
            $("#modal_content_account").fadeOut();
        });
        $("#add_btn").click(function () {
            $("#add_form").submit();
        });
        //修改
        var id;
        var password;
        var username;
        $('.img_icon').click(function () {
            id=$(this).data("id");
            username=$(this).data("username");
            password=$(this).data("password");
            $("#modal_view").fadeIn();
            $("#modal_content_edit").fadeIn();
        });

        $("#close2").click(function () {
            $("#modal_view").fadeOut();
            $("#modal_content_edit").fadeOut();
        });
        $("#pre_password").keyup(function () {
            if($("#pre_password").val()==password) {
                $("#password").attr("disabled", false);
                $("#password2").attr("disabled", false);
            }else {
                $("#password").val("");
                $("#password2").val("");
                $("#password").attr("disabled", true);
                $("#password2").attr("disabled", true);
                $("#edit_btn").attr("disabled","disabled");
            }
        })
        $("#password2,#password").keyup(function () {
            if($("#password").val()==$("#password2").val()) {
                if($("#password").val()!=null&&$("#password").val()!=null)
                    if($("#password").val().length>3&&$("#password").val().length>3)
                    $("#edit_btn").attr("disabled",false);
            }else {
                $("#edit_btn").attr("disabled","disabled");
            }
        })
        $("#edit_btn").click(function () {
            $(window).attr('location','${ctx}/account_update.action?id='+id+'&username='+username+'&password='+$("#password").val());
        })
    });
</script>
</body>
</html>