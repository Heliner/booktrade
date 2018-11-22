package com.cqut.service;

import com.cqut.entity.Book;
import com.cqut.entity.User;

import java.util.List;

public interface BookService {
    public void insert(Book book, User curUser) throws Exception;


    /*需要就行权限校验*/
    public void update(Book book, User curUser) throws Exception;

    public void delete(Book book, User curUser) throws Exception;

    public void add(Book book, User curUser) throws Exception;

    public List<Book> selectBooksByUser(User user) throws Exception;

    public List<Book> selectBooksByCategory(String category);

    public List<Book> selectBooksByCategoryId(Long cid) throws Exception;

    /*        <!--id name cid booktype-->*/

    public List<Book> selectBooksByInfo(Book book) throws Exception;

    public List<Book> selectBooksByInfoLimit(Book book, Long start, Long end) throws Exception;

    public void batchDeleteBook(List<Long> idList, User curUser) throws Exception;

    public Long countByInfo(Book book) throws Exception;

    public Book selectByPrimaryKey(Long bid) throws Exception;
}
