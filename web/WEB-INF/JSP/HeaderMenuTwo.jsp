<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/"; %>
<%String jhsBasePath = basePath + "";%>

<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="<%=jhsBasePath%>Thrid-Party/font-awesome-4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="<%=jhsBasePath%>Thrid-Party/font-awesome-4.7.0/css/font-awesome.css">
    <link rel="stylesheet" href="<%=jhsBasePath%>css/staticSet.css">
    <style>
        /*HeaderAndMenu开始*/
        header {
            background: ghostwhite;
        }

        #message_nav {
            width: 100%;
            background: black;
            height: 50px;
            display: none;
        }

        .userInfoNav {
            width: 100%;
            height: 30px;
            background: #4b4d52;
        }

        .userInfoNav a {
            color: white;
        }

        .userInfoNav dl {
            clear: left;
            margin-right: 10px;
        }

        .userInfoNav dt, dd {
            float: right;
            margin-right: 20px;
        }

        #main {
            margin: 0 auto;
        }

        #serviceNav {
            background: #f2f1ea;
            width: 100%;
            height: 47px;
        }

        #serviceNav ul {
            margin-left: 120px;
            padding-top: 18px;

        }

        #serviceNav li {
            float: left;
            margin-left: 90px;
            height: 29px;
        }

        #serviceNav a {
            color: #3f4247;
            height: 40px;
            font-size: 16px;
        }

        #serviceNav li:hover {
            font-weight: 600;
            border: solid #b5aa9a;
            border-width: 0 0 2px 0;
        }

        /*#serviceNav a:hover {*/
        /*border: solid #b5aa9a;*/
        /*border-width: 0 0 2px 0;*/
        /*}*/

        #searchAndLogo {
            width: 100%;
            height: 100px;
            background: azure;

        }

        #searchAndLogo form {
            float: right;
            margin-right: 20%;
            margin-top: 30px;
        }

        #searchAndLogo img {
        }

        #searchAndLogo span {
            width: 20px;
            height: 20px;
        }

        #mainLogo {
            width: 360px;
            height: 100px;
            margin-left: 200px;
        }

        .aForI i {
            padding-top: 4px;
        }

        .fa-search {
            width: 50px;
            height: 46px;
        }

        .searchInput {
            width: 300px;
            height: 46px;
        }

        .searchSubmit {
            margin-left: 60px;
            width: 80px;
            height: 44px;
            color: #d6d2d2;
            font-size: 15px;
            border: 2px solid #3f4247;
            background: #4b4d52;
            overflow: hidden;
        }

        .searchSubmit:hover {
            font-size: 17px;
        }

        /*HeaderAndMenu完毕*/
    </style>
</head>
<header id="headerMenuTwo">
    <div id="message_nav"></div>
    <nav id="userInfo" class="userInfoNav">
        <dl>
            <dt><a href="<%=jhsBasePath%>user/logout">[ 退出 ]</a></dt>
            <dd><a href="<%=jhsBasePath%>user/mystall.do"> ||我的书架</a></dd>
            <dd><a>${user.username}</a></dd>
        </dl>
    </nav>
    <div id="searchAndLogo">
        <img src="<%=jhsBasePath%>img/logo.png" id="mainLogo" width="360px" height="100px">
        <form>
            <!--<img src="./img/search.png" width="36px" height="34px">-->

            <!--<a class="aForI">                </a>-->

            <i class="fa fa-search fa-lg"></i>
            <input class="searchInput" type="search" placeholder="搜图书...">
            <input class="searchSubmit" type="submit" value="搜索" style="margin-left: 10px">
        </form>
    </div>
    <div id="serviceNav">
        <ul>
            <li><a href="<%=jhsBasePath%>home.do">首页</a></li>
            <li><a href="<%=jhsBasePath%>book/store">二手书区</a></li>
            <li><a href="<%=jhsBasePath%>book/beg">求购区</a></li>
            <li><a href="">服务区</a></li>
        </ul>
    </div>
</header>
</html>