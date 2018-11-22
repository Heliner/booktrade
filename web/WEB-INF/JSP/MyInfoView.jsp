<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/"; %>
<%String jhsBasePath = basePath + "";%>
<html>
<head>
    <meta charset="UTF-8">
    <title>${user.username}的个人信息</title>

    <link rel="stylesheet" href="<%=jhsBasePath%>Thrid-Party/font-awesome-4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="<%=jhsBasePath%>Thrid-Party/font-awesome-4.7.0/css/font-awesome.css">
    <link rel="stylesheet" href="<%=jhsBasePath%>css/staticSet.css">

    <style>

        #stallAndBegBook {
            width: 70%;
            position: relative;
            /*top: 0;*/
            left: 25%;
            overflow: hidden;
            margin-top: 80px;
            padding-bottom: 80px
        }

        .serviceNav {
            margin-top: 30px;
            margin-left: 5%;
            width: 15%;
            background: #fafafa;
            position: absolute;
        }

        .serviceNav ul {
            margin: 50px auto;
        }

        .serviceNav li {
            margin-top: 40px;
            margin-left: 50px;
        }

        .serviceNav a {
            background: #fafafa;
            color: black;
            font-size: 20px;
            font-weight: 200;
        }

        .contact {
            margin-top: 40px;
            margin-left: 5%;
            height: auto;
        }

        .contact p {
            margin-top: 20px;
            font-size: 18px;
            color: #626a55;
            font-weight: bold;
        }

        .contact input {
            height: 25px;
            color: #626a55;
        }

        .submitBtn {
            margin-top: 20px;
            width: 100px;
            color: #f2f1ea;
        }

        .resetBtn {
            margin-top: 20px;
            width: 100px;
        }

        /*修饰图标*/
        .mars-stroke {
            width: 100px;
            height: 100px;
        }
    </style>
</head>

<body>
<div class="wrapper">

    <div class="main">
        <div class="serviceNav">
            <ul>
                <li><a href="#">个人信息</a></li>
                <li><a href="#">我的书摊</a></li>
                <li><a href="#">我的求书</a></li>
            </ul>
        </div>
        <div id="stallAndBegBook">
            <div class="contact">
                <form action="" method="post">
                    <p><i class=""></i>姓名：<input type="text" name="userName" value="${user.username}"></p>
                    <p>
                        <i class="fa fa-mars"></i>
                        性别:
                        <input type="radio" name="sex" value="女"><span class="sex">女</span>
                        <input type="radio" name="sex" value="m"><span class="sex">男</span>
                        <input type="radio" name="sex" value="s"><span class="sex">保密</span>
                    </p>
                    <p>
                        <i class="fa fa-whatsapp"></i>
                        联系：<input class="num" name="call" value="${user.tel}"></input></p>
                    <p>
                        <%--  private Long id;
                          private String username;
                          private String name;
                          private String password;
                          private String sex;
                          private String tel;
                          private String address;
                          private String major;
                          private String qqAssociated;
                          private String campus;
                          private String email;--%>
                        <i class="fa fa-address-book"></i>
                        住址：<input class="address" name="address" value="${user.address}" type="text"></p>
                    <p>
                        <i class="major-icon"></i>
                        年级专业：<input class="major" name="major" value="${user.major}" type="text">
                    </p>
                    <p>
                        <i></i>
                        QQ:<input class="qq" name="qqAssociated" value="${user.qqAssociated}" type="text">
                    </p>
                    <!--<button class="modifyBtn" onclick="modify()">修改</button>-->
                    <input type="submit" class="submitBtn">
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>