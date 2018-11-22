package com.cqut.controller;


import com.cqut.entity.Book;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class TestController {

    @RequestMapping("/uploadS")
    void uploadS(Book book, @RequestParam(name = "images", required = false) MultipartFile[] files) {
        System.out.println(book.getId());
        System.out.println(files.length);
    }
}
