package com.cqut.util;


import com.cqut.entity.Book;

import java.util.List;

public class BookPageHelper {
    private static final Long DEATULT_PAGE_SIZE = 10L;
    private static final Long DEFAULT_COUNT = 0L;
    private List<Book> list;
    private Long firstPage = 1L;
    private Long prePage;
    private Long currentPage = 1L;
    private Long nextPage;
    private Long totalPage;            //总页数 末页
    private Long count = 0L;                //总条数
    private Long pageSize = 10L;        //每页多少条
    private Long start = 0l;

    private BookPageHelper() {
        this.currentPage = 1L;
        this.start = 0L;
    }

    /**
     * 需要curPage,pageSize
     *
     * @param curPage
     * @param pageSize
     */
    public BookPageHelper(Long curPage, Long pageSize, Long count) {
        this.currentPage = curPage;
        this.pageSize = pageSize;
        this.count = count;
    }

    public Long getStart() {
        return (currentPage - 1) * pageSize;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    public Long getFirstPage() {
        return firstPage;
    }

    public void setFirstPage(Long firstPage) {
        this.firstPage = firstPage;
    }

    public Long getPrePage() {
        return currentPage - 1 <= 0 ? 1 : currentPage - 1;
    }

    public void setPrePage(Long prePage) {
        this.prePage = prePage;
    }

    public Long getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Long currentPage) {
        this.currentPage = currentPage;
    }

    public Long getNextPage() {
        return currentPage + 1 > totalPage ? totalPage : currentPage + 1;
    }

    public void setNextPage(Long nextPage) {
        this.nextPage = nextPage;
    }


    public Long getCount() {
        return this.count;
    }

    public void setCount(Long count) {
        this.count = count;
        this.totalPage = count / pageSize;
    }

    public Long getPageSize() {
        if (pageSize <= 0)
            pageSize = 10L;
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    public Long getEnd() {
        return getStart() + getPageSize();
    }

    public List<Book> getList() {
        return list;
    }

    public void setList(List<Book> list) {
        this.list = list;
    }


    public void setDeaultStart() {
        if (start == null)
            this.start = 0L;
    }

    public void setDeaultPageSize() {
        this.pageSize = DEATULT_PAGE_SIZE;
    }

    public Long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Long totalPage) {
        this.totalPage = totalPage;
    }

    public static BookPageHelper generateWithCheck(BookPageHelper bookPageHelper, Long count) {

        BookPageHelper re = new BookPageHelper();
        if (bookPageHelper == null || bookPageHelper.getCurrentPage() < 1L)
            re.setCurrentPage(1L);
        else
            re.setCurrentPage(bookPageHelper.getCurrentPage());
        if (bookPageHelper == null || bookPageHelper.getPageSize() < 0)
            re.setPageSize(DEATULT_PAGE_SIZE);
        else
            re.setPageSize(bookPageHelper.getPageSize());
        if (bookPageHelper == null || bookPageHelper.getCount() < 0)
            re.setCount(count);
        else
            re.setCount(bookPageHelper.getCount());
        re.setTotalPage(count / re.getPageSize() + 1);
        return re;
    }
}
