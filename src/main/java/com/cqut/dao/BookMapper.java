package com.cqut.dao;

import com.cqut.entity.Book;
import org.apache.ibatis.annotations.Param;

import javax.annotation.Resource;
import java.util.List;

public interface BookMapper {
    /*选择*/
    public List<Book> selectBooksByInfo(Book queryBook) throws Exception;

    /*id选择*/
    public Book selectBookById(@Param("id") Long id) throws Exception;

    public List<Book> selectBookByUid(Long uid) throws Exception;

    /*id删除*/
    public void deleteBookById(@Param("id") Long id) throws Exception;

    /*Info删除*/
//    public void deleteBookByInfo(Book deleteBookInfo)throws  Exception;

    /*更新*/
    public void updateBook(Book updateBook) throws Exception;

    /*插入*/
    public Long insertBook(Book book) throws Exception;


    /*批量插入*/
    /*    public void batchInsertBook(List<Book> bookList);*/
    public Long countBooksByUserId(Long userId) throws Exception;

    public List<Book> selectBooksByInfoLimit(@Param(value = "book") Book book, @Param(value = "start") Long start, @Param(value = "end") Long end) throws Exception;

    public Book selectByPrimaryKey(@Param("id") Long id) throws Exception;

    public Long countByInfo(@Param(value = "book") Book book) throws Exception;

//    public Long count();

//    public Long countByCategories(String category);

}
