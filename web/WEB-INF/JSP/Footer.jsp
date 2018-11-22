<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<%String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/"; %>
<%String jhsBasePath = basePath + "";%>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>公共底部</title>
    <style>
        footer {

            text-align: center;
            margin-top: 70px;
            border-top: #d6d2d2 solid 1px;
            padding-top: 30px;
        }

        footer a {
            display: block;
            margin-top: 3px;
            color: #626a55;
        }
    </style>
</head>
<body>
<footer>
    <a href="#"><i class="fa fa-at fa-lg"></i>2018-2019 二手书交易</a>
    <a href="#">意见反馈&nbsp;&nbsp;&nbsp;联系我们&nbsp;&nbsp;&nbsp;隐私权声明&nbsp;&nbsp;&nbsp;使用条款</a>
</footer>
</body>
</html>