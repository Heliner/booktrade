package com.cqut.controller;

import com.alibaba.fastjson.JSONObject;
import com.cqut.entity.Book;
import com.cqut.entity.BookImage;
import com.cqut.entity.Category;
import com.cqut.entity.User;
import com.cqut.exception.IllegalOperationException;
import com.cqut.result.AjaxResult;
import com.cqut.service.BookImageService;
import com.cqut.service.CategoryService;
import com.cqut.util.BookPageHelper;
import com.cqut.service.BookService;
import com.cqut.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;

import static com.cqut.util.Config.MAX_ONCE_IMG_UPLOAD;
import static com.cqut.util.ModelAndViewGenerator.GENERATE_FAIL_VIEW;
import static com.cqut.util.ResultGenerator.*;
import static com.cqut.util.SessionUtil.HttpUtil.getCurUser;
import static com.cqut.util.SessionUtil.HttpUtil.updateProjectBasePath;

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;
    @Autowired
    BookImageService bookImageService;
    @Autowired
    CategoryService categoryServiceImpl;

    //接受多个Id进行删除
    /*fucking autowired*/
    /*@Autowired*/
//    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    /*无视图界面*/
    @RequestMapping(value = "/delete")
    @ResponseBody
    //fixme 开启权限校验
    // done
    public ModelAndView delete(@RequestParam(value = "bookIds", required = false) List<Long> idList, HttpServletRequest request, ModelAndView modelAndViewS) {
        updateProjectBasePath(request);
        User curUser = getCurUser(request);
//        System.out.println(idList.get(0));
        idList.stream().forEach(System.out::println);

        try {
            bookImageService.deleteImageList(idList, curUser);
//            删除数据库中的书本信息后，图片中bid会为空或者记录直接被删除
            bookService.batchDeleteBook(idList, curUser);
            //todo
        } catch (Exception e) {
            e.printStackTrace();
            return GENERATE_FAIL_VIEW("数据有误");
        }
        modelAndViewS.setViewName("redirect:/user/mystall.do");
        return modelAndViewS;
    }

    /*更新转跳*/
    /*只需要部分信息{id}*/
    @RequestMapping(value = "/updateBook/{bid}")
    public ModelAndView editBookDo(HttpServletRequest request, @PathVariable(value = "bid") Long bid) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/EditBookInfoTODO");
        Book book = new Book();
        book.setId(bid);
        try {
            book = bookService.selectByPrimaryKey(bid);
            modelAndView.addObject("book", book);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelAndView;
    }


    //    @RequestMapping(value = "/", method = RequestMethod.PUT)
    /*更新方法*/
    @RequestMapping(value = "/put")
    @ResponseBody
    public AjaxResult update(HttpServletRequest request, ModelAndView modelAndView, Book book, @RequestParam(value = "images", required = false) MultipartFile[] files) {
        User curUser = getCurUser(request);
        book.setUser(curUser);
        //fixme
//        if (!checkImgType(files))
//            return GENERATE_FAILED_MESSAGE("图片格式不正确,图片只能为jpg");
        int count = 0;
        try {
            /*更新图片 id 不会改变*/
            bookService.update(book, curUser);
            count++;
            bookImageService.updateImages(files, book, curUser);
            count++;
        } catch (IllegalOperationException illegalOperationException) {
            GENERATE_FAILED_MESSAGE(illegalOperationException.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            if (count == 1) {
                System.out.println("这是错误的情况");
            }
            if (count == 2) {
            }
            return GENERATE_DEFAULT_FAILED_MESSAGE();
        }
        /*modelAndView.setViewName("forward:/user/mystall.do");
        return modelAndView;*/
        return GENERATE_SUCCESS_RESULT("修改正确");
    }

    //fixme
    private boolean checkImgType(MultipartFile[] files) {
        boolean flag = true;
        HashSet<String> baseImgType = new HashSet<>();
        baseImgType.add("jpg");
        baseImgType.add("png");
        baseImgType.add("bmp");
        for (int i = 0; i < files.length; i++) {
            MultipartFile file = files[i];
            System.out.println("img-type : " + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.')));
            if (!baseImgType.contains(file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.')))) {
                flag = false;
                break;
            }
        }
        return flag;
    }


    //    @RequestMapping(value = "/", method = RequestMethod.GET)
    /*获取多本图书*/
    /*获取转跳*/
    @RequestMapping(value = "/get.do")
    /*    public AjaxResult select(HttpServletRequest request, @RequestBody@RequestParam(value = "id") Book book, @RequestBody@RequestParam(value = "hp") BookPageHelper bookPageHelper) {*/
    /*获取多本图书*/
    public ModelAndView select(HttpServletRequest request, Book book, BookPageHelper bookPageHelper, ModelAndView modelAndView) {
        List<Book> bookList;
        try {
            bookPageHelper.setCount(bookService.countByInfo(book));
            bookList = bookService.selectBooksByInfoLimit(book, bookPageHelper.getStart(), bookPageHelper.getEnd());
        } catch (Exception e) {
            e.printStackTrace();
            return GENERATE_FAIL_VIEW();
        }
        modelAndView.addObject("bookList", bookList);
        return modelAndView;
    }


    /*获取单本图书*/
    /*获取转跳*/
    @RequestMapping(value = "/get/{id}")
    public ModelAndView selectOne(HttpServletRequest request, @PathVariable(value = "id") Long bid, ModelAndView modelAndView) {
        modelAndView.setViewName("/BookDetailR");
        try {
            modelAndView.addObject("book", bookService.selectByPrimaryKey(bid));
        } catch (Exception e) {
            e.printStackTrace();
            return GENERATE_FAIL_VIEW();
        }
        return modelAndView;
    }

    /*无入参，无出参*/
    /*添加转跳*/
    @RequestMapping(value = "/upload.do")
    public ModelAndView uploadDo(ModelAndView modelAndView) {
        modelAndView.setViewName("/UploadSell");
        modelAndView.addObject("categories", categoryServiceImpl.allCategories());
        return modelAndView;
    }

    /*添加*/
//    @RequestMapping(value = "/", method = RequestMethod.POST)
    //done

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView add(Book book, @RequestParam(value = "images", required = false) MultipartFile[] multipartFiles, ModelAndView modelAndView, HttpServletRequest request) {
        try {
            /*if (!checkImgType(multipartFiles)) {
                return GENERATE_FAIL_VIEW("不支持的图片类型");
            }*/
            updateProjectBasePath(request);
            //fixme 设置最大上传个数
            /*设置最大的上传个数*/
            System.out.println("bookId" + book.getId());
            if (multipartFiles.length > MAX_ONCE_IMG_UPLOAD)
                return GENERATE_FAIL_VIEW("上传图片个数不能超过 " + MAX_ONCE_IMG_UPLOAD + " 个");
            bookService.insert(book, getCurUser(request));
            BookImage bookImage = new BookImage();
            bookImage.setBook(book);
            bookImageService.insert(multipartFiles, book, getCurUser(request));
        } catch (Exception e) {
            e.printStackTrace();
            return GENERATE_FAIL_VIEW();
        }
        /*不写forward不行.*/
        modelAndView.setViewName("forward:/user/mystall.do");
        return modelAndView;
    }

    /*获取该类别的图书 转跳*/
    @RequestMapping(value = "/category/{id}")
    public ModelAndView categoryDo(ModelAndView modelAndView, @PathVariable(value = "id") Long cid, @RequestParam(required = false) BookPageHelper bookPageHelper) {
        Category category = new Category(cid);
        Book book = new Book();
        book.setCategory(category);
        try {
            Long count = bookService.countByInfo(null);
            bookPageHelper = BookPageHelper.generateWithCheck(bookPageHelper, count);

            modelAndView.setViewName("/CategoryBookViewListR");
            modelAndView.addObject("bookList", bookService.selectBooksByInfoLimit(book, bookPageHelper.getStart(), bookPageHelper.getEnd()));
        } catch (Exception e) {
            e.printStackTrace();
            GENERATE_FAIL_VIEW();
        }
        return modelAndView;
    }

    @RequestMapping(value = "/search")
    public ModelAndView search(ModelAndView modelAndView, Book book, BookPageHelper bookPageHelper) {
        try {
            bookPageHelper.setCount(bookService.countByInfo(book));
            modelAndView.addObject("bookList", bookService.selectBooksByInfoLimit(book, bookPageHelper.getStart(), bookPageHelper.getEnd()));
        } catch (Exception e) {
            e.printStackTrace();
            return GENERATE_FAIL_VIEW();
        }
        return modelAndView;
    }

    @RequestMapping(value = "/beg")
    public String beg() {
        return "/BegBookViewTODO";
    }

    @RequestMapping(value = "/store")
    public ModelAndView store(ModelAndView modelAndView, BookPageHelper bookPageHelper) {
        modelAndView.setViewName("/BookStore");
        try {
            Long count = bookService.countByInfo(null);
            Long curPage = bookPageHelper.getCurrentPage();
            Long pageSize = bookPageHelper.getPageSize();
            if (bookPageHelper == null)
                bookPageHelper = new BookPageHelper(curPage, pageSize, count);
            else {
                bookPageHelper = BookPageHelper.generateWithCheck(bookPageHelper, count);
            }

            bookPageHelper.setList(bookService.selectBooksByInfoLimit(null, bookPageHelper.getStart(), bookPageHelper.getPageSize()));
            modelAndView.addObject("bookPage", bookPageHelper);
            System.out.println("size:" + bookPageHelper.getList().size());
        } catch (Exception e) {
            e.printStackTrace();
            return GENERATE_FAIL_VIEW();
        }
        return modelAndView;
    }

    @RequestMapping(value = "/categories")
    public AjaxResult getCategory(@RequestBody Book book) {
        JSONObject data = new JSONObject();
        Category category = null;
        try {
            category = bookService.selectByPrimaryKey(book.getId()).getCategory();
//            log.info("request: book/category/get , bookId: " + book.getId() + " , category:" + category.toString());
            if (category.getId() != 0) {
                data.put("categoryId", category.getId());
                return ResultGenerator.GENERATE_SUCCESS_RESULT(data);
            } else {
                return ResultGenerator.GENERATE_FAILED_MESSAGE("无效的Category！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return GENERATE_DEFAULT_FAILED_MESSAGE();
    }


}
