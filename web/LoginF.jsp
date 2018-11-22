<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<form action="" method="post">
    <input name="password" id="password">
    <input name="username" id="username">
    <button id="loginButton">登录</button>
</form>
<p id="errorInfo"></p>
<%System.out.println(request.getContextPath());%>
<script src="<%=request.getContextPath()%>/js/jquery-3.2.js"></script>
<script type="text/javascript">
    $(function () {
        $('#loginButton').click(function (event) {
            $('errorInfo').html("");
            var un = $('#username').val();
            var pw = $('#password').val();
            /*if (studentid_.length == 0 || password_.length == 0) {
                $('#errorInfo').html("学号或密码不能为空！");
                return false;
            }*/
            var user_ = {'username': un, 'password': pw};
            var jsonData = JSON.stringify(user_);
            alert(jsonData)
            console.log(jsonData);
            $.ajax({
                type: "POST",
                url: "/user/login",
                async: false,
                dataType: 'json',
                contentType: 'application/json;charset=UTF-8',
                data: jsonData,
                success: function (result) {
                    console.log(result)
                    alert(result)
                }
            })
        })
    })

</script>
</body>
</html>