$(function () {
    //选择对应书本进行选择删除操作
    var bookIdArr = [], bookId;
    $("i#selectBook").on("click", function () {
        bookId = $(this).siblings("input").attr("value");
        console.log("bookId" + bookId);
        //判断数组里是否有bookId,无则返回-1
        if ($.inArray(bookId, bookIdArr) == -1) {
            $(this).css("background-image", "url(img/delete1.png)");
            bookIdArr.push(bookId);
            console.log(bookIdArr);
        } else {
            $('#selectBook').cssText = "fa fa-check-circle-o fa-2x";
            // $(this).css("background-image", "url(img/delete2.png)");

            bookIdArr = $.grep(bookIdArr, function (val) {
                return val != bookId;
            });
            console.log(bookIdArr);
        }
    });


    // 点击删除图标，确认是否删除
    var flag = false;
    $("#delete-book").on("click", function () {
        flag = confirm("确定删除所选择的二手书吗？");
        if (flag == true) {
            console.log(bookIdArr);
            // 为防止使用DELETE、PUT参数传输失败，先将type设为POST，再在data中进行修改
            $.ajax({
                type: "POST",
                url: "/book/delete",
                async: false,
                traditional: true,
                data: {"bookIds": bookIdArr, _method: 'DELETE'},
                success: function (msg) {
                    flag = msg;
                    alert("删除成功！");
                    location.href = "myBookshelf.do";
                }
            });
        }
    });


});
