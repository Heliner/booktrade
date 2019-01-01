<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<%String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/"; %>
<%String jhsBasePath = basePath + "";%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>书籍·良品</title>
    <link rel="stylesheet" type="text/css" href="<%=jhsBasePath%>css/book.css">
    <script type="text/javascript" src="<%=jhsBasePath%>js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="<%=jhsBasePath%>js/jquery-3.2.js"></script>
</head>
<body>
<!-- 导航 -->
<jsp:include page="HeaderMenuTwo.jsp" flush="false"/>
<!-- 图书分类 -->
<div id="container">

    <div id="book-container">
        <h3 class="book-title">
            <c:if test="${not empty bookPage.list[0].category.category}">
                精品图书
            </c:if>
            <c:if test="${bookPage.list[0].category.category}">
                <a href="#">| ${bookPage.list[0].category.category}</a>
            </c:if>

        </h3>

        <ul class="book-lists">
            <c:forEach items="${bookPage.list}" var="book" varStatus="bookStatus">
                <li class="book-list">
                    <a href="/book/get/${book.id}" class="book-pic" target="_blank">
                        <img src="<%=request.getContextPath()%>${book.bookImageList[0].directory}">
                            <%--<img>--%>
                    </a>
                    <a href="#" class="book-info">
                        <h5 class="book-name">${book.name}</h5>
                        <span class="book-detail">${book.description}</span>
                    </a>
                    <span class="book-price">
                        ￥${book.price}
						<a href="/book/get/${book.id}" class="book-buy">立即下单</a>
                     </span>
                </li>
            </c:forEach>
        </ul>
    </div>
    <div class="page-btn">
        <a href="${pageContext.request.contextPath}/book/store?currentPage=${bookPage.firstPage}">首页</a>
        <a href="${pageContext.request.contextPath}/book/store?currentPage=${bookPage.prePage}" class="page-pre">上一页</a>
        <a href="${pageContext.request.contextPath}/book/store?currentPage=${bookPage.nextPage}"
           class="page-next">下一页</a>
        <a href="${pageContext.request.contextPath}/book/store?currentPage=${bookPage.totalPage}"
           class="page-end">尾页</a>
    </div>
</div>
<jsp:include page="Footer.jsp" flush="false"/>
</body>
</html>
