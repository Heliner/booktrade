package com.cqut.util;


import java.util.List;

public abstract class PageHelper<T> {
    private List<T> list;
    private Long firstPage = 1L;
    private Long prePage;
    private Long currentPage = 1L;
    private Long nextPage;
    private Long totalPage;            //总页数 末页
    private Long count;                //总条数
    private Long pageSize = 10L;        //每页多少条
    private Long start = 0l;

    public PageHelper() {
        this.currentPage = 0L;
        this.start = 0L;
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

    public Long getTotalPage() {
        return count % pageSize != 0 ? count / pageSize + 1 : count / pageSize;
    }

    public void setTotalPage(Long totalPage) {
        this.totalPage = totalPage;
    }

    public Long getCount() {
        return this.count;
    }

    public void setCount(Long count) {
        this.count = count;
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

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }


    public void setDeaultStart() {
        if (start == null)
            this.start = 0L;
    }
}
