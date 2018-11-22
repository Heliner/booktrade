package com.cqut.serviceImpl;

import com.cqut.common.OperationTip;
import com.cqut.dao.BookMapper;
import com.cqut.entity.Book;
import com.cqut.entity.Category;
import com.cqut.entity.User;
import com.cqut.exception.IllegalOperationException;
import com.cqut.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public void insert(Book book, User curUser) throws Exception {
        book.setUser(curUser);
        bookMapper.insertBook(book);
    }

    /*需要就行权限校验*/
    @Override
    public void update(Book book, User curUser) throws Exception {
        if (book == null) {
            throw new IllegalOperationException(OperationTip.LOSE_VITAL_INFORMATION);
        }
        if (!curUser.equals(book.getUser())) {
            throw new IllegalOperationException(OperationTip.ILLEGAL_OPERATION);
        }
        bookMapper.updateBook(book);
    }

    @Override
    public void delete(Book book, User curUser) throws Exception {
        if (book == null)
            throw new IllegalOperationException(OperationTip.LOSE_VITAL_INFORMATION);
        //fixme open
        /*if (!curUser.equals(book.getUser()))
            throw new IllegalOperationException(OperationTip.ILLEGAL_OPERATION);
        */

        bookMapper.deleteBookById(book.getId());
    }

    @Override
    public void add(Book book, User curUser) throws Exception {
        if (book == null)
            throw new IllegalOperationException(OperationTip.LOSE_VITAL_INFORMATION);
        book.setUser(curUser);
        bookMapper.insertBook(book);
    }

    @Override
    public List<Book> selectBooksByUser(User user) throws Exception {
        Long uid = user.getId();
        return bookMapper.selectBookByUid(uid);
    }

    @Deprecated
    @Override
    public List<Book> selectBooksByCategory(String category) {

        return null;
    }

    @Override
    public List<Book> selectBooksByCategoryId(Long cid) throws Exception {
        Book book = new Book();
        book.setCategory(new Category(cid));
        return bookMapper.selectBooksByInfo(book);
    }

    @Override
    public List<Book> selectBooksByInfo(Book book) throws Exception {
        return bookMapper.selectBooksByInfo(book);
    }

    @Override
    public List<Book> selectBooksByInfoLimit(Book book, Long start, Long size) throws Exception {
        if (start == null || size == null || size < 0 || start < 0) {
            throw new IllegalOperationException(OperationTip.INVALID_PARAMETER);
        }
        return bookMapper.selectBooksByInfoLimit(book, start, size);
    }

    @Override
    //TODO mybatis实现批量删除F
    public void batchDeleteBook(List<Long> idList, User curUser) throws Exception {
        if (idList == null)
            return;
        for (Long id : idList) {
            Book book = new Book();
            book.setId(id);
            this.delete(book, curUser);
        }
    }

    @Override
    public Long countByInfo(Book book) throws Exception {
        return bookMapper.countByInfo(book);
    }

    @Override
    public Book selectByPrimaryKey(Long bid) throws Exception {
        return bookMapper.selectByPrimaryKey(bid);
    }

    ;

}
