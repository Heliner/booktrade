package com.cqut.dao;

import com.cqut.entity.BookImage;

import java.util.List;

public interface BookImageMapper {

    void batchDelete(List<BookImage> bookImageList);

    void batchInsert(List<BookImage> bookImageList);

    List<BookImage> selectByBidList(List<Long> bookIdList);

    void deleteByBid(BookImage bookImage);
}
