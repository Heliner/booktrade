<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<%String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/"; %>
<%String jhsBasePath = basePath + "";%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>求书区</title>
    <link rel="stylesheet" type="text/css" href="<%=jhsBasePath%>css/book.css">
    <script type="text/javascript" src="<%=jhsBasePath%>js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript">
        /*使用JQuery实现复用*/
        $(document).ready(function () {
            $('#headerTwo').load('./MiniHeader.html')
            $('#footer').load('./Footer.html');
        });
    </script>
</head>
<body>
<!-- 导航 -->
<header id="headerMenuTwo">
</header>

<!-- 图书分类 -->
<div id="container">
    <div id="book-container">
        <h3 class="book-title"><a href="http://localhost:8080/goAskBookStore.do#">| 所有求书信息</a></h3>
        <img src="none" width="80%" height="100px " alt="该功能正在紧急制作中!">
        <ul class="book-lists">
        </ul><!--  book-list end -->
    </div> <!-- book-container end-->
    <span class="page-btn">
			<a href="#" class="page-top">首页</a>
			<a href="http://localhost:8080/goAskBookStore.do?start=-16" class="page-pre">上一页</a>
			<a href="http://localhost:8080/goAskBookStore.do?start=16" class="page-next">下一页</a>
			<a href="http://localhost:8080/goAskBookStore.do?start=32" class="page-end">尾页</a>
		</span>
</div><!-- container end-->
<footer id="footer">
</footer>

<script type="text/javascript" src="js/jquery-3.2.js"></script>
<script type="text/javascript">
    $(function () {
        //菜单特效
        var index = 0
        $("#book-menu-toggle").on("click", function () {
            if (index === 0) {
                $("#book-menu").css("left", "1px");
                $(this).css("left", "178px");
                index = 1;
            } else {
                $("#book-menu").css("left", "-180px");
                $(this).css("left", "1px");
                index = 0;
            }
        });

        //点击菜单，对应类别颜色加深
        $(".book-class li").on("click", function () {
            //加粗当前点击的li
            $(this).css("font-weight", "600").siblings("li").css("font-weight", "500");
        });
    })
</script>


</body>
</html>