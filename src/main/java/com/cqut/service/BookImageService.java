package com.cqut.service;

import com.cqut.entity.Book;
import com.cqut.entity.BookImage;
import com.cqut.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BookImageService {


    void insert(MultipartFile[] multipartFiles, Book book, User curUser) throws IOException;

    @Deprecated
    void updateImages(MultipartFile[] files, Book book, User curUser, List<BookImage> oldBookImageList) throws IOException;

    void updateImages(MultipartFile[] files, Book book, User curUser) throws IOException;


    void deleteOneImage(User curUser, List<BookImage> bookImageList) throws IOException;

    void deleteImageList(List<Long> bookIdList, User curUser) throws IOException;

    /*该方法由Book实现*/
//    public void insertImages();
}
