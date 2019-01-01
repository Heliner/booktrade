<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<%String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/"; %>
<%String jhsBasePath = basePath + "";%>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>登录</title>

    <link rel="stylesheet" href="<%=jhsBasePath%>css/Login.css" type="text/css">
</head>
<body>
<div class="wrapper">
    <div class="loginContent">
        <form class="contentForm" id="contentForm">
            <p><input type="text" placeholder="用户名" id="username" name="username" class="un"></p>
            <p><input type="text" placeholder="密码" id="password" name="password" class="pw"></p>
            <p><input type="text" placeholder="验证码" id="valCode" name="valCode" class="valCode">
                <a href="#" id="valA">
                    <img class="valCodeImg" id="valCodeImg" onclick="randomImageAAA()">
                </a>
            </p>
            <p><a href="<c:url value="/user/register.do"/>" target="_self">还没有账号？注册一个</a></p>
            <p id="errorInfo"></p>
            <p><input type="button" value="登录" id="submitBtn"></p>
        </form>
    </div>
    <script type="text/javascript" src="<%=jhsBasePath%>js/jquery-3.2.js"></script>
    <!--实现异步验证-->
    <script type="text/javascript">
        $(function () {
            $("#submitBtn").on("click", function (event) {
                var errorInfo = $('#errorInfo');
                errorInfo.html('');
                console.log(errorInfo);
                password_ = $('#password').val();
                username_ = $('#username').val();
                valCode_ = $('#valCode').val();
                console.log("valCOde" + valCode_);
                flag = false;
                if (username_.length == 0) {
                    errorInfo.html('请输入用户名');
                    flag = true;
                }
                else if (password_.length == 0) {
                    errorInfo.html('请输入密码');
                    flag = true;
                }
                else if (valCode_.length = 0) {
                    errorInfo.html('验证码有误');
                    flag = true;
                }
                if (flag)
                    return false;

                // var m = new Map();
                /*提交多重对象*/
                var user_ = {"user": {"password": password_, "username": username_}, "valCode": valCode_};
                console.log(user_);
                var jsonData = JSON.stringify(user_);
                // jsonData = $('#contentForm').serialize();
                // console.log("jsonData");
                console.log(jsonData);
                $.ajax({
                    type: "POST",
                    url: "/user/login",
                    async: false,
                    traditional: true,
                    data: jsonData,
                    dataType: "json",
                    contentType: "application/json;charset=UTF-8",
                    // contentType: "application/x-www-form-urlencoded;charset=UTF-8",
                    success: function (result) {
                        console.log(result);
                        event.preventDefault();
                        alert("登录成功！");
                        window.location.href = "<%=basePath%>home.do";
                    },
                    error: function (resutl) {
                        event.preventDefault();
                        errorInfo.html('' + resutl.responseJSON.message);
                    }
                });
            });
        });

        function randomImageAAA() {
            randImage = document.getElementById("valCodeImg");
            randImage.src = "<%=jhsBasePath%>user/ImgVal?math=" + Math.random();
        }

        randomImageAAA();
    </script>
</div>
</body>
</html>
