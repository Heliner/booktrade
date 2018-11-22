<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<%String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/"; %>
<%String jhsBasePath = basePath + "";%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>${bookList[0].category.category}</title>
    <link rel="stylesheet" href="<%=jhsBasePath%>css/staticSet.css">
    <link rel="stylesheet" href="<%=jhsBasePath%>css/CategoryBook/CategoryBook.css">
    <script type="text/javascript" src="<%=jhsBasePath%>js/jquery-3.3.1.min.js"></script>
</head>
<body>
<div id="wrapper">
    <jsp:include page="MiniHeader.jsp"/>
    <div id="main">
        <div class="CBVLMainImg">
        </div>
        <div id="bookInformations">
            <ul>
                <li>
                    <div class="bookInformation">
                        <div id="bookCategory">
                            <h3>| ${bookList[0].category.category}</h3>
                            <a class="fr appendH" href=""><%-->>更多--%></a>
                        </div>
                        <ul class="bookRack">
                            <c:forEach items="${bookList}" var="book">
                                <li class="bookLi">
                                    <a class="bookLiAImg">
                                        <img style="width: 150px;height: 150px;"
                                             src="${book.bookImageList[0].directory}"></a>
                                    <a class="bookDesc">
                                        <h5>${book.name}</h5>
                                        <span>${book.description}</span>
                                    </a>
                                    <div class="priceAndOrderDiv">
                                        <s class="bookPriceCancel">￥${book.originalPrice}</s>
                                        <span class="bookPriceCur">￥${book.price}</span>
                                        <a href="/book/get/${book.id}" class="OrderBookNow">立即下单</a>
                                    </div>
                                </li>
                            </c:forEach>
                            <li class="bookLi">
                                <a class="bookLiAImg"><img style="width: 150px;height: 150px;"
                                                           src="img/book1.jpg"></a>
                                <a class="bookDesc"><h5>芒果街上的小屋</h5>
                                    <span>这是一个很长很长的很长很长的很长很长的很长很长的内容</span>
                                </a>
                                <div class="priceAndOrderDiv">
                                    <s class="bookPriceCancel">￥20.0</s>
                                    <span class="bookPriceCur">￥9.9</span>
                                    <a href="" class="OrderBookNow">立即下单</a>
                                </div>

                            </li>
                        </ul>
                    </div>
                </li>
                <li>
                    <div class="bookInformation">
                    </div>
                </li>
                <li>
                    <div class="bookInformation">

                    </div>
                </li>
            </ul>
        </div>
        <div id="pageHelper">
            <a href="">首页</a>
            <a href="">上一页</a>
            <a href="">下一页</a>
            <a href="">尾页</a>
        </div>
    </div>
    <jsp:include page="Footer.jsp"/>
</div>
</body>
</html>