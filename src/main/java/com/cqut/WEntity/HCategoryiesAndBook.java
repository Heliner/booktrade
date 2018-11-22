package com.cqut.WEntity;

import com.cqut.entity.Book;
import com.cqut.entity.Category;

import java.util.List;

public class HCategoryiesAndBook {
    Category category;
    List<Book> bookList;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
}
