<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<%String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/"; %>
<%String jhsBasePath = basePath + "";%>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>编辑书本信息</title>
    <link rel="stylesheet" type="text/css" href="<%=jhsBasePath%>css/reset.css">
    <link rel="stylesheet" type="text/css" href="<%=jhsBasePath%>css/upload.css">
    <script type="text/javascript" src="<%=jhsBasePath%>js/jquery-3.3.1.min.js"></script>

</head>
<body>
<!-- 导航条 -->
<jsp:include page="./MiniHeader.jsp"/>
<div class="titleName">
    <h3>编辑二手书信息</h3>
</div>

<div class="container">
    <form enctype="multipart/form-data" id="bookForm">
        <input type="hidden" name="id" value="18" id="bookId">
        <p>
            <%--
             private Long id;
             private String name;
             private int bookType;
             private double price;
             private double originalPrice;
             private User user;
             private String author;
             private String press;
             private String version;
             private Double degree;
             private Date publishDate;
             private String description;
             private Date shelvesDate;
             private Category category;
             private List<BookImage> bookImageList;
             private Long favoritesCount;
            --%>

            <span class="pic-icon">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
            修改书图片： <input type="file" id="book-file" name="image">
            <img id="book-pic" src="${book.bookImageList[0].directory}">
        </p>
        <p>
            <span class="name-icon">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
            书&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：<input type="text" name="name"
                                                                                  value="${book.name}" id="bookName">
        </p>
        <p>
            <span class="author-icon">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
            作&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;者：<input type="text" name="author"
                                                                                  value="${book.author}"
                                                                                  id="bookAuthor">
        </p>
        <p>
            <span class="name-icon">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
            分&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;类：<select id="category"
                                                                                   name="${book.category.id}">

            <option value="1" id="bookCategory">小说 / 文学 / 语言文学</option>

            <option value="2" id="bookCategory">历史 / 地理 / 艺术</option>

            <option value="3" id="bookCategory">政治 / 法律 / 军事</option>

            <option value="4" id="bookCategory">哲学 / 心理 / 宗教</option>

            <option value="5" id="bookCategory">经济 / 社科 / 综合</option>

            <option value="6" id="bookCategory">童书 / 生活 / 体育</option>

            <option value="7" id="bookCategory">工程技术 / 互联网</option>

            <option value="8" id="bookCategory">教材 / 教辅 / 考试</option>

            <option value="9" id="bookCategory">自然科学 / 医药卫生</option>

        </select>
        </p>
        <p>
            <span class="origin-icon">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
            书&nbsp;本&nbsp;&nbsp;原&nbsp;价：<input type="text" name="originalPrice" value="${book.originalPrice}"
                                                id="bookOriginalPrice">
        </p>
        <p>
            <span class="price-icon">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
            书&nbsp;本&nbsp;&nbsp;售&nbsp;价：<input type="text" name="price" value="${book.price}" id="bookPrice">
        </p>
        <p>
            <span class="press-icon">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
            出&nbsp;&nbsp;&nbsp;&nbsp;版&nbsp;&nbsp;&nbsp;社：<input type="text" name="press" value="${book.press}"
                                                                 id="bookPress">
        </p>
        <p>
            <span class="time-icon">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
            出&nbsp;版&nbsp;&nbsp;时&nbsp;间：<input type="month" name="publishDate" value="${book.publishDate}"
                                                id="bookDate">
        </p>
        <p>
            <span class="version-icon">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
            印&nbsp;刷&nbsp;&nbsp;版&nbsp;本：<input type="text" name="version" value="${book.version}" id="bookVersion">
        </p>
        <p>
            <span class="condition-icon">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
            书&nbsp;本&nbsp;&nbsp;品&nbsp;相：<input type="text" name="degree" value="${book.degree}" id="bookDegree">
        </p>
        <p>
            <span class="desc-icon">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
            书&nbsp;本&nbsp;&nbsp;描&nbsp;述：<input type="text" name="description" value="${book.description}"
                                                id="bookDescription">
        </p>
        <input type="button" class="submit-btn" id="book-submit" value="提交">
    </form>
</div>
<jsp:include page="Footer.jsp"/>
<script type="text/javascript" src="<%=jhsBasePath%>js/jquery-3.2.js"></script>
<script type="text/javascript" src="<%=jhsBasePath%>js/jquery.form.js"></script>
<script type="text/javascript">
    window.onload = function () {
        var id = $("#bookId").val();
        var id_ = {"id": id};
        var jsonData = JSON.stringify(id_);
        $.ajax({
            type: "POST",
            url: "/books/categories",
            async: false,
            dataType: "json",
            contentType: "application/json;charset=UTF-8",
            data: jsonData,
            success: function (result) {
                var opts = document.getElementById("category");
                var categoryId = result.data.categoryId;
                console.log(categoryId);
                if (categoryId != "") {
                    for (var i = 0; i < opts.options.length; i++) {
                        if (categoryId == opts.options[i].value) {
                            opts.options[i].selected = "selected";
                            break;
                        }
                    }
                }
            }
        });
    };

    $("#book-submit").click(function (event) {
        $("#bookForm").ajaxSubmit({
            type: "PUT",
            url: "/book/",
            async: false,
            dataType: "json",
            success: function (result) {
                if (result.resultCode == 1) {
                    alert("修改成功");
                } else {
                    alert(result.message);
                }
            }
        });
    });

    $(function () {
        $("#book-file").change(function () {
            var filePath = $(this).val().split("\\");
            var len = filePath.length;
            var file = filePath[len - 1];
            if (!file) {
                filePath = "img/loadPic.png"
            }
            filePath = "img/" + file;
            // console.log(filePath);
            $("#book-pic").attr("src", filePath);
        })
    });


</script>


</body>
</html>