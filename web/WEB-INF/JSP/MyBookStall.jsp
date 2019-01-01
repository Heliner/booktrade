<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<%String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/"; %>
<%String jhsBasePath = basePath + "";%>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>我的书摊</title>

    <link rel="stylesheet" href="<%=jhsBasePath%>Thrid-Party/font-awesome-4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="<%=jhsBasePath%>Thrid-Party/font-awesome-4.7.0/css/font-awesome.css">
    <link rel="stylesheet" href="<%=jhsBasePath%>css/staticSet.css">
    <link rel="stylesheet" href="<%=jhsBasePath%>css/MyBookStall.css">

    <script type="text/javascript" src="<%=jhsBasePath%>js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="<%=jhsBasePath%>js/jquery-3.2.js"></script>

</head>
<body>
<div id="wrapper">
    <jsp:include page="MiniHeader.jsp" flush="false"/>
    <div id="main">
        <div class="serviceNav">
            <ul>
                <li><a href="#">个人信息</a></li>
                <li><a href="#">我的书摊</a></li>
                <li><a href="#">我的求书</a></li>
            </ul>
        </div>
        <div id="stallAndBegBook">
            <div class="cardBtn">
                <p class="upload-icon"></p>
                <a id="sell-upload" href="/book/upload.do"> <i class="fa fa-cloud-upload fa-2x"></i>上传书至书摊</a>
                <a id="ask-upload" href="#">上传求书信息</a>
                <a id="delete-book" href="#"><i class="fa fa-close fa-2x"></i>删除二手书</a>
            </div>
            <div id="bookStallContainer">
                <div id="bookStall">
                    <ul class="bookUl">
                        <%--bookList--%>
                        <c:forEach items="${bookList}" var="book">
                            <li class="bookLi">
                                <a class="bookLiAImg">
                                    <img style="width: 120px;height: 120px;"
                                         src="<%=request.getContextPath()%>${book.bookImageList[0].directory}">
                                </a>
                                <a class="bookDesc">
                                    <h5>${book.name}</h5>
                                    <span>${book.description}</span>
                                </a>
                                <input type="hidden" value="${book.id}">
                                <div class="priceAndOrderDiv">
                                    <s class="bookPriceCancel">￥${book.originalPrice}</s>
                                    <span class="bookPriceCur">￥${book.price}</span>
                                </div>
                                <i class="fa fa-square-o fa-2x" id="selectBook"></i>
                                <a href="/book/updateBook/${book.id}" class="editBook">
                                    <i class="fa fa-edit fa-2x"></i>
                                </a>
                            </li>
                        </c:forEach>
                        <%--   <li class="bookLi">
                               <a class="bookLiAImg">
                                   <img style="width: 120px;height: 120px;" src="img/book1.jpg">
                               </a>
                               <a class="bookDesc">
                                   <h5>芒果街上的小屋</h5>
                                   <span>这是一个很长很长的很长很长的很长很长的很长很长的内容</span>
                               </a>
                               <div class="priceAndOrderDiv">
                                   <s class="bookPriceCancel">￥20.0</s>
                                   <span class="bookPriceCur">￥9.9</span>
                                   <!--<a href="" class="OrderBookNow">立即下单</a>-->
                               </div>
                               <i class="fa fa-square-o fa-2x"></i>
                               <a href="#" class="editBook"><i class="fa fa-edit fa-2x"></i></a>
                           </li>
                           <li class="bookLi">
                               <a class="bookLiAImg"><img style="width: 120px;height: 120px;" src="img/book1.jpg"></a>
                               <a class="bookDesc"><h5>芒果街上的小屋</h5>
                                   <span>这是一个很长很长的很长很长的很长很长的很长很长的内容</span>
                               </a>
                               <div class="priceAndOrderDiv">
                                   <s class="bookPriceCancel">￥20.0</s>
                                   <span class="bookPriceCur">￥9.9</span>
                                   <!--<a href="" class="OrderBookNow">立即下单</a>-->
                               </div>

                           </li>
                           <li class="bookLi">
                               <a class="bookLiAImg"><img style="width: 120px;height: 120px;" src="img/book1.jpg"></a>
                               <a class="bookDesc"><h5>芒果街上的小屋</h5>
                                   <span>这是一个很长很长的很长很长的很长很长的很长很长的内容</span>
                               </a>
                               <div class="priceAndOrderDiv">
                                   <s class="bookPriceCancel">￥20.0</s>
                                   <span class="bookPriceCur">￥9.9</span>
                                   <!--<a href="" class="OrderBookNow">立即下单</a>-->
                               </div>

                           </li>
                           <li class="bookLi">
                               <a class="bookLiAImg"><img style="width: 120px;height: 120px;" src="img/book1.jpg"></a>
                               <a class="bookDesc"><h5>芒果街上的小屋</h5>
                                   <span>这是一个很长很长的很长很长的很长很长的很长很长的内容</span>
                               </a>
                               <div class="priceAndOrderDiv">
                                   <s class="bookPriceCancel">￥20.0</s>
                                   <span class="bookPriceCur">￥9.9</span>
                                   <!--<a href="" class="OrderBookNow">立即下单</a>-->
                               </div>

                           </li>
                           <li class="bookLi">
                               <a class="bookLiAImg"><img style="width: 120px;height: 120px;" src="img/book1.jpg"></a>
                               <a class="bookDesc"><h5>芒果街上的小屋</h5>
                                   <span>这是一个很长很长的很长很长的很长很长的很长很长的内容</span>
                               </a>
                               <div class="priceAndOrderDiv">
                                   <s class="bookPriceCancel">￥20.0</s>
                                   <span class="bookPriceCur">￥9.9</span>
                                   <!--<a href="" class="OrderBookNow">立即下单</a>-->
                               </div>

                           </li>--%>
                    </ul>
                </div> <!-- sell-book-end -->
            </div>
        </div>
    </div>
    <jsp:include page="Footer.jsp"/>

</div>

</body>
<script type="text/javascript" src="<%=jhsBasePath%>js/mybookshelf.js"></script>
</html>