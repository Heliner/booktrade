package com.cqut.serviceImpl;

import com.cqut.common.OperationTip;
import com.cqut.dao.BookMapper;
import com.cqut.entity.Book;
import com.cqut.entity.BookImage;
import com.cqut.entity.Category;
import com.cqut.entity.User;
import com.cqut.exception.IllegalOperationException;
import com.cqut.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        return wrapperBooks(bookMapper.selectBookByUid(uid));
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
        return wrapperBooks(bookMapper.selectBooksByInfo(book));
    }

    @Override
    public List<Book> selectBooksByInfo(Book book) throws Exception {
        return wrapperBooks(bookMapper.selectBooksByInfo(book));
    }

    @Override
    public List<Book> selectBooksByInfoLimit(Book book, Long start, Long size) throws Exception {
        if (start == null || size == null || size < 0 || start < 0) {
            throw new IllegalOperationException(OperationTip.INVALID_PARAMETER);
        }
        return wrapperBooks(bookMapper.selectBooksByInfoLimit(book, start, size));
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
        return wrappBook(bookMapper.selectByPrimaryKey(bid));
    }

    private List<Book> wrapperBooks(List<Book> bookList) {
        Random random = new Random();

        bookList.forEach(book -> {
            List<BookImage> bookImages = new ArrayList<>();
            BookImage bookImage = new BookImage();
            bookImages.add(bookImage);
            bookImage.setDirectory("\\BOOK_IMG\\1\\" + (random.nextInt(4) + 1) + ".jpg");
            book.setBookImageList(bookImages);
        });
        return bookList;
    }

    private static final Book wrappBook(Book book) {
        List<BookImage> bookImages = new ArrayList<>();
        BookImage bookImage = new BookImage();
        Random random = new Random();
        bookImage.setDirectory("\\BOOK_IMG\\1\\" + (random.nextInt(4) + 1) + ".jpg");
        bookImages.add(bookImage);

        book.setBookImageList(bookImages);
        return book;
    }
}