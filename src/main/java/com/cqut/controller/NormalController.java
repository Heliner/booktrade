package com.cqut.controller;

import com.cqut.WEntity.HCategoryiesAndBook;
import com.cqut.entity.Book;
import com.cqut.entity.Category;
import com.cqut.service.BookService;
import com.cqut.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.LinkedList;
import java.util.List;

import static com.cqut.util.ModelAndViewGenerator.GENERATE_FAIL_VIEW;

@RequestMapping
@Controller
public class NormalController {
    @Autowired
    BookService bookServiceImpl;
    @Autowired
    CategoryService categoryServiceImpl;

    /*前面需要加上/*/
    /*/WEB-INF/JSP + /login +.jsp*/
    @RequestMapping(value = "/home.do")
    public ModelAndView home(ModelAndView modelAndView) {
        /*查出所有category*/
        modelAndView.setViewName("/HomeR");
        List<Category> categoryList = categoryServiceImpl.allCategories();
        System.out.println(categoryList.size());
        List<HCategoryiesAndBook> hCategoryiesAndBookList = new LinkedList<>();
        try {
            for (Category category : categoryList) {
                Book book = new Book();
                book.setCategory(category);
                HCategoryiesAndBook hCategoryiesAndBook = new HCategoryiesAndBook();
                hCategoryiesAndBook.setBookList(bookServiceImpl.selectBooksByInfoLimit(book, 0L, 15L));
                hCategoryiesAndBook.setCategory(category);
                hCategoryiesAndBookList.add(hCategoryiesAndBook);
                System.out.println(hCategoryiesAndBook.getBookList().size());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return GENERATE_FAIL_VIEW();
        }
        modelAndView.addObject("homePage", hCategoryiesAndBookList);
        return modelAndView;
    }
}
