<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<%!Long count = 0L; %>
<%String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/"; %>
<head>
    <meta charset="UTF-8">
    <title>mini-header</title>
    <link rel="stylesheet" href="<%=basePath%>css/staticSet.css">
    <%System.out.println("basePath+:" + basePath);%>
    <style>
        header {
            width: 100%;
            display: flex;
            flex-wrap: nowrap;
            background: #3f4247;
            text-align: center;
            padding-bottom: 3px;
        }

        .navService {
            width: 26%;
            margin-top: 9px;
        }

        .navService ul {
            margin-left: 2%;
        }

        .navService li {
            float: left;
            margin-left: 10%;
        }

        .navService a {
            color: #f2f1ea;
            font-size: 14px
        }

        .navSearch {
            display: flex;
            justify-content: center;
            margin-top: 7px;
            margin-left: 1%;
        }

        #userInfoNav {
            width: 26%;
            height: 30px;
            display: flex;
            flex-wrap: nowrap;
            margin-left: 20%;
            margin-top: 10px;
        }

        #userInfoNav a {
            color: #d6d2d2;
            font-size: 14px;
        }

        #userInfoNav dl {
            clear: left;
            margin-right: 10px;
        }

        #userInfoNav dt, dd {
            float: right;
            margin-right: 40px;
        }

        .searchInput {
            margin-left: 20px;
            width: 250px;
            height: 30px;
        }

        .searchSubmit {
            margin-left: 10px;
            width: 60px;
            height: 30px;
            color: #d6d2d2;
            font-size: 15px;
            border: 2px solid #3f4247;
            background: #4b4d52;
            overflow: hidden;
        }

    </style>
</head>
<body>
<header>
    <%count++;%>
    <%System.out.println("java out: count :" + count);%>
    <nav class="navService">
        <ul>
            <li><a href="">首页</a></li>
            <li><a href="">二手书区</a></li>
            <li><a href="">求购区</a></li>
            <li><a href="">服务区</a></li>
        </ul>
    </nav>
    <div class="navSearch">
        <form>
            <input type="search" class="searchInput" placeholder="输入图书...">
            <input type="submit" class="searchSubmit" value="搜索">
        </form>
    </div>
    <div id="userInfoNav" class="userInfoNav">
        <dl>
            <dt><a>[ 退出 ]</a></dt>
            <dd><a> ||我的书架</a></dd>
            <dd><a>余文乐</a></dd>
        </dl>
    </div>
</header>
</body>
</html>