<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<%String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/"; %>
<%String jhsBasePath = basePath + "";%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>校园二手书交易平台</title>
    <link rel="stylesheet" href="<%=jhsBasePath%>Thrid-Party/font-awesome-4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="<%=jhsBasePath%>Thrid-Party/font-awesome-4.7.0/css/font-awesome.css">
    <link rel="stylesheet" href="<%=jhsBasePath%>css/staticSet.css">
    <link rel="stylesheet" href="<%=jhsBasePath%>css/HomeR.css">
    <script type="text/javascript" src="<%=jhsBasePath%>js/jquery-3.3.1.min.js"></script>
    <script src="<%=jhsBasePath%>js/carousel.js"></script>
    <link rel="stylesheet" href="<%=jhsBasePath%>css/carousel.css">
    <link rel="icon" href="<%=jhsBasePath%>img/icon.jpg">
</head>
<body>
<div id="wrapper">
    <jsp:include page="HeaderMenuTwo.jsp" flush="false"/>
    <%--<%@include file="HeaderMenuTwo.jsp" fl%>--%>
    <div id="main">
        <div id="carouselMain">
            <div id="carousel" style="position: relative; overflow: hidden;height: 350px;left: 115px">
                <span id="carousel_pre" class="fa fa-angle-left fa-3x"></span>
                <span id="carousel_next" class="fa fa-angle-right fa-3x"></span>
                <ul id="carousel_page">
                    <li class="carousel_pagination" id=""></li>
                    <li class="carousel_pagination" id="carousel_active"></li>
                    <li class="carousel_pagination" id=""></li>
                    <li class="carousel_pagination" id=""></li>
                    <li class="carousel_pagination" id=""></li>
                    <li class="carousel_pagination" id=""></li>
                </ul>
                <div id="carousel_container" style="width: 600%; left: -100%; height: 600px;">
                    <div class="carousel_img-item" style="width: 16.6667%;"><img
                            src="<%=jhsBasePath%>img/silder/slider1.jpg">
                    </div>
                    <div class="carousel_img-item" style="width: 16.6667%;"><img
                            src="<%=jhsBasePath%>img/silder/slider2.jpg">
                    </div>
                    <div class="carousel_img-item" style="width: 16.6667%;"><img
                            src="<%=jhsBasePath%>img/silder/slider3.jpg">
                    </div>
                    <div class="carousel_img-item" style="width: 16.6667%;"><img
                            src="<%=jhsBasePath%>img/silder/slider4.jpg">
                    </div>
                </div>
            </div>
            <div id="bookMenu">
                <h4>图书导航</h4>
                <ul class="book-class">
                    <c:forEach items="${homePage}" var="hp" varStatus="statu">
                        <li>
                            <a href="#book-part-${statu.index}" title="book-part-${statu.index}">${hp.category.category}
                            </a>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
        <%--需要categoryList 和对应category的多个book--%>
        <div id="bookInformations">
            <ul>
                <c:forEach items="${homePage}" var="hp" varStatus="statu">
                    <li id="book-part-${statu.index}">
                        <div class="bookInformation">
                            <div class="bookCategory">
                                <h3>|${hp.category.category}</h3>
                                <a class="fr appendH" href="/book/category/${hp.category.id}">>>更多</a>
                            </div>
                            <ul class="bookRack">
                                <c:forEach items="${hp.bookList}" var="book">
                                    <li class="bookLi">
                                        <a class="bookLiAImg">
                                            <img style="width: 150px;height: 150px;"
                                                 src="${book.bookImageList[0].directory}">
                                        </a>
                                        <a class="bookDesc">
                                            <h5>${book.name}</h5>
                                            <span style="white-space:nowrap">${book.description}</span>
                                        </a>
                                        <div class="priceAndOrderDiv">
                                            <s class="bookPriceCancel">￥${book.originalPrice}</s>
                                            <span class="bookPriceCur">￥${book.price}</span>
                                            <a href="/book/get/${book.id}" target="_self" class="OrderBookNow">立即下单</a>
                                        </div>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </li>
                </c:forEach>

            </ul>
        </div>
    </div>
    <%--<jsp:include page="Footer.html" flush="false"/>--%>
    <jsp:include page="./Footer.jsp"/>
</div>
<script>
    var carousel = new Carousel({
        wrap: document.getElementById("carousel"),
        urlArr: ["<%=jhsBasePath%>img/silder/slider1.jpg", "<%=jhsBasePath%>img/silder/slider2.jpg", "<%=jhsBasePath%>img/silder/slider3.jpg", "<%=jhsBasePath%>img/silder/slider4.jpg", "<%=jhsBasePath%>img/silder/slider5.jpg"]
    });
    carousel;
</script>
</body>
</html>