<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<%String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/"; %>
<%String jhsBasePath = basePath + "";%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>${book.name}---${book.user.username}</title>
    <link rel="stylesheet" href="<%=jhsBasePath%>css/staticSet.css">
    <style>

        .container {
            margin-top: 20px;
            min-width: 1100px;
            width: 100%;
            display: flex;
            flex-direction: row;
            justify-content: center;
            flex-wrap: wrap;
            margin-bottom: 20px;
        }

        .bookPics {
            width: 22%;
            height: 300px;
            margin-left: 4%;
            text-align: center;
        }

        .bookPics img {
            width: 270px;
            height: 270px;
            margin-top: 10px;
            padding: 20px;
            border-bottom: #626a55 solid 2px;
        }

        /*修饰书籍信息开始*/
        .bookInfo {
            margin-left: 2%;
            width: 36%;
        }

        .bookInfo p {
            margin-top: 7px;

        }

        .bookName {
            font-size: 21px;

        }

        .bookPublics {

            color: burlywood;
            font-size: 15px;
        }

        .someInfo {
            background: #f3f0e9;
            padding-left: 10px;
        }

        .bookInfoBlock {
            font-size: 13px;
            color: gray;
            overflow: hidden;
        }

        .bookInfoBlock span, s {
            margin-left: 9px;
        }

        .curPrice {
            font-size: 20px;
            color: #880b16;
        }

        .bookPriceOriginal {
            font-size: 15px;
        }

        .bookDegree {
            font-size: 16px;
            font-style: revert;
            color: black;
        }

        .bookDesc {
            color: #846251;
            font-size: 15px;
            line-height: 15px;
            height: 45px;
            overflow: hidden;
        }

        .bookDesc:after {
            display: inline;
            content: "...";
            font-size: 15px;
        }

        .dataIssued {
            font-size: 15px;
        }

        .buyNow {
            display: block;
            margin-top: 20px;
            margin-left: 18px;
            padding-top: 6px;
            width: 190px;
            height: 40px;
            border: 2px solid #4b4d52;
            /* background-color: #4b4d52; */
            color: #4b4d52;
            border-radius: 7px;
            text-align: center;
            cursor: pointer;
            transition: 0.3s;
        }

        .buyNow:hover {
            color: white;
            background: #4b4d52;
        }

        /*修饰书籍信息结束*/
        /*修饰用户信息*/

        .sellerInfo {
            width: 20%;
            margin-left: 2%;
        }

        .sellerInfo span, p {
            margin-top: 15px;
        }

        .sellerInfo a {
            font-size: 14px;
        }

        .sellName {
            text-align: center;
            border-bottom: #626a55 solid 2px;
        }

        .sellerName {
            font-size: 19px;
            background: #66756b;
            color: #f2f1ea;
            padding: 4px 30px;
        }

        .sellerContact {
            display: block;
        }

        .sellerContact a {
            margin-left: 25px;
            font-size: 14px;
            padding: 3px 6px;
            background: #f3f0e9;
            color: black;
            border-radius: 4px;
        }

        .sellerMoreInfo {
            background: #fafafa;
        }

        .sellerMoreInfo a {
            color: #6f391e;

            margin-left: 20px;
        }

        .sellerStore {
            display: block;
            margin-top: 30px;
            text-align: center;
        }

        .sellerStore a {
        }

        /*修饰用户信息*/
    </style>
    <script type="text/javascript" src="<%=jhsBasePath%>js/jquery-3.3.1.min.js"></script>
</head>
<body>
<div id="wrapper">
    <!--header2-->
    <jsp:include page="MiniHeader.jsp"/>
    <!--header2-->
    <div class="container">
        <div class="bookPics">
            <img class="bookPrincipalView" src="${book.bookImageList[0].directory}">
        </div>
        <div class="bookInfo">
            <p class="bookName">${book.name}</p>
            <p class="bookPublics"> ${book.getAuthor()} 著 / ${book.getPress()} / ${book.getPublishDate()}
                / ${book.getVersion()}</p>
            <div class="someInfo">
                <p class="bookInfoBlock">售价 <span class="curPrice">￥${book.getPrice()}</span></p>
                <p class="bookInfoBlock">定价 <s class="bookPriceOriginal">￥${book.price}</s></p>
                <p class="bookInfoBlock">品相<span class="bookDegree">${book.degree}新</span></p>
            </div>
            <p class="bookInfoBlock">商品描述
                <span class="bookDesc">
                    ${book.description}
                </span>
            </p>
            <p class="bookInfoBlock">上架时间<span class="dataIssued">${book.shelvesDate}</span></p>
            <a class="buyNow" href="#">立即联系卖家进行购买</a>
            <!--<p class="bookInfoBlock"><a class="buyNow">立即联系卖家购买</a></p>-->
        </div>
        <div class="sellerInfo">
            <p class="sellName"><a class="sellerName">${book.user.username}</a></p>
            <p class="sellerContact">联系<a href="">在线联系</a></p>
            <div class="sellerMoreInfo">
                <p>电话<a class="sellerInfoA">${book.user.tel}</a></p>
                <p>校区<a class="sellerInfoA">${book.user.campus}</a></p>
                <p>宿舍<a class="sellerInfoA">${book.user.address}</a></p>
                <p>好评率<a class="sellerInfoA">90.5%</a></p>
            </div>
            <p class="sellerStore">
                <a href="#"> [ 去看看卖家的其他二手书 ]</a>
                <%--//fixme 添加书摊--%>
            </p>
        </div>
        <div class="bookSynopsis"></div>
    </div>
    <jsp:include page="Footer.jsp"/>
</div>
</body>
</html>