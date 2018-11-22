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
    <script type="text/javascript" src="<%=jhsBasePath%>js/jquery-3.3.1.min.js"></script>
    <style>
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

        .bookPriceCancel {
            float: left;
            font-size: 11px;
            margin-left: 10px;
            margin-top: 15px;
        }

        .bookPriceCur {
            font-size: 20px;
            float: left;
            color: #880b16;
            margin-top: 5px;
        }

        #stallAndBegBook {
            width: 70%;
            position: relative;
            /*top: 0;*/
            left: 25%;
            margin-top: 20px;
            border: 1px solid transparent;
        }

        /*修饰上传书摊*/
        .cardBtn {
            margin-left: 60%;
            margin-top: 20px;
            /*border-bottom: 2px solid #66756b;*/
        }

        .cardBtn a {
            margin-right: 20px;
        }

        /*修饰完毕*/

        /*修饰bookList开始*/
        #bookStallContainer {
            margin-top: 20px;
            border-top: 1px solid #66756b;
        }

        .bookUl {
            width: 100%;
            height: auto;
            display: flex;
            flex-wrap: wrap;
            justify-content: flex-start;
            flex-direction: row;
            padding-left: 20px;
            margin-top: 30px;
        }

        .bookList {
            width: 20%;
            min-width: 20%;
            height: 250px;
            overflow: visible;
            border: 1px solid #eae1d2;
            margin-right: 35px;
            display: flex;
            justify-content: flex-start;
            flex-direction: column;
            flex-wrap: wrap;
            align-items: flex-start;
            margin-bottom: 30px;
            position: relative;
        }

        /*修饰书籍信息开始*/

        .bookLi {
            min-width: 19%;
            text-align: center;
            height: 325px;
            overflow: hidden;
            border: 1px solid #eae1d2;
            margin-right: 25px;
            display: flex;
            justify-content: flex-start;
            flex-direction: column;
            flex-wrap: wrap;
            align-items: flex-start;
            margin-bottom: 30px;
            position: relative;
        }

        .bookLiAImg {
            margin: 15px auto;
            padding-bottom: 10px;
            border-bottom: gray solid 1px;
            width: 160px;
            height: 130px;
        }

        .bookDesc {
            margin: 0 auto;
            height: 80px;
            width: 185px;
        }

        .bookDesc h5 {
            color: #ae6d6a;
            font-size: 17px;
            margin-top: 10px;
            margin-bottom: 10px;
        }

        /*未解决多余的字体*/
        .bookDesc span {
            height: 40px;
            font-size: 15px;
            display: inline-block;
            line-height: 20px;
            width: 185px;
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
            word-break: keep-all;
        }

        .bookDesc span:after {
            display: inline;
            content: "...";
            font-size: 15px;
        }

        .priceAndOrderDiv {
            margin-top: 10px;
            margin-left: 10%;
        }

        .editBook {
            width: 20px;
            height: 20px;
            position: absolute;
            margin: 3px 3px;
            left: 84%;
        }

        /*修饰font-awsome*/
        .fa {
            /*color: #6f391e;*/
            color: #66756b;

        }

        .fa-square-o {
            position: absolute;
            margin: 5px;
        }

        .fa-edit {
        }

        .fa-cloud-upload {
            color: #66756b;
        }

        .fa-close {
            color: #66756b;

        }

        /*修饰font-awsome结束*/

        /*
                .selected {
                    width: 20px;
                    height: 20px;
                    background: red;
                    position: absolute;
                    margin: 3px 3px;
                }*/

        /*修饰书籍信息结束*/
    </style>
    <script type="text/javascript" src="<%=jhsBasePath%>js/jquery-3.2.js"></script>

    <%-- <script type="text/javascript">
         /*使用JQuery实现复用*/
         $(document).ready(function () {
             $('#headerTwo').load('./MiniHeader.html')
             $('#footer').load('./Footer.html');
         });

     </script>
 --%>
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
                                    <img style="width: 120px;height: 120px;" src="${book.bookImageList[0].directory}">
                                </a>
                                <a class="bookDesc">
                                    <h5>${book.name}</h5>
                                    <span>${book.description}</span>
                                </a>
                                <input type="hidden" value="${book.id}">
                                <div class="priceAndOrderDiv">
                                    <s class="bookPriceCancel">￥${book.originalPrice}</s>
                                    <span class="bookPriceCur">￥${book.price}</span>
                                    <a href="" class="OrderBookNow">立即下单</a>
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