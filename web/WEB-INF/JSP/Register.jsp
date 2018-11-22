<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<%String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/"; %>
<%String jhsBasePath = basePath + "";%>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>注册</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        .wrapper {
            text-align: center;

            background: url("");
        }

        .loginContent {
            font-size: 14px;
            text-align: center;
            width: 800px;
            background: transparent;
            margin: 0 auto;
            border: transparent solid 1px;
        }

        .contentForm {
            margin-top: 20px;
        }

        .contentForm p {
            margin-top: 20px;
        }

        .un {
            width: 300px;
            height: 40px;
        }

        .pw {
            width: 300px;
            height: 40px;
        }

        .valCode {
            width: 100px;
            height: 40px;
        }

        .valCodeImg {
            width: 150px;
            height: 40px;
            float: left;
            margin-left: 50px;
            color: red;
            background: #2091dc;
        }

        .contentForm {
            width: 400px;
            height: 300px;
            margin-left: auto;
            margin-right: auto;
            margin-top: 40px;
            border: transparent 2px solid;
            background: transparent;
        }

        #submitBtn {
            width: 300px;
            height: 30px;
            background: #2091dc;
            border: 1px #2091dc solid;
        }

        #timeTip {
            width: 3em;
            color: #6f391e;
        }

        #errorInfo {
            color: firebrick;
            font-weight: 700;
        }
    </style>
</head>
<body>
<div class="wrapper">
    <div class="loginContent">
        <form class="contentForm">
            <p><input type="text" placeholder="请输入用户名" id="username" class="un"></p>
            <p><input type="password" placeholder="请输入密码" id="password" class="pw"></p>
            <p><input type="password" placeholder="请输入密码" id="cPassword" class="pw"></p>
            <p><input type="email" placeholder="请输入E-mail" id="email" class="pw"></p>
            <p><input type="text" placeholder="邮箱验证码" id="valCode" class="valCode">
                <button
                        class="valCodeImg" value="获取验证码" type="button"><span id="timeTip">获取验证码</span></button>
            </p>
            <p id="errorInfo"></p>
            <p><a href="<c:url value="/user/login.do"/>">已有账号？登录</a></p>
            <p>
                <button value="注册" id="submitBtn">注册</button>
            </p>
        </form>
    </div>

</div>
</body>
<script type="text/javascript" src="<%=jhsBasePath%>js/jquery-3.2.js"></script>
<script type="text/javascript">
    $(function () {
        $("#submitBtn").on("click", function (event) {
            var errorInfo = $('#errorInfo');
            errorInfo.html('');
            console.log(errorInfo);
            password_ = $('#password').val();
            cPassword_ = $('#cPassword').val();
            email = $('#email').val();
            username_ = $('#username').val();
            valCode_ = $('#valCode').val();
            flag = false;
            if (username_.length == 0) {
                errorInfo.html('请输入用户名')
                flag = true;
            }
            else if (password_.length == 0) {
                errorInfo.html('请输入密码');
                flag = true;
            }
            else if (!password_ == cPassword_) {
                errorInfo.html('两次密码需要一致')
                flag = true;
            }
            else if (valCode_.length = 0) {
                errorInfo.html('验证码有误')
                flag = true;
            }
            if (flag)
                return false;

            // var m = new Map();
            var user_ = {
                "password": password_,
                "email": email,
                "username": username_,
            };
            var jsonData = JSON.stringify({
                "user": user_, "valCode": valCode_, "cPassword": cPassword_
            });
            alert(jsonData);
            $.ajax({
                type: "POST",
                url: "/user/login",
                async: false,
                traditional: true,
                data: jsonData,
                success: function (result) {
                    if (result.resultCode == 1) {
                        event.preventDefault();
                        alter("注册成功");
                        location.href = "<%=basePath%>login.do";
                    } else {
                        event.preventDefault();
                        $('#errorInfo').html(result.message);
                    }
                }
            });
        });
    });
</script>

</html>