$(function () {
    //选择对应书本进行选择删除操作
    var bookIdArr = [], bookId;
    $("i#selectBook").on("click", function () {
        var selectBook = document.getElementById("selectBook");
        bookId = $(this).siblings("input").attr("value");
        //判断数组里是否有bookId,无则返回-1
        if ($.inArray(bookId, bookIdArr) == -1) {
            console.log(this);
            console.log(this.id)
            this.className = "fa fa-check-circle-o fa-2x";
            // selectBook.className = "fa fa-check-circle-o fa-2x";

            bookIdArr.push(bookId);
            console.log("1:::" + bookIdArr);
        } else {
            this.className = "fa fa-square-o fa-2x";
            // selectBook.className = "fa fa-square-o fa-2x";
            bookIdArr = $.grep(bookIdArr, function (val) {
                return val != bookId;
            });
            console.log("2:::" + bookIdArr);
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
