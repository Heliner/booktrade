package com.cqut.serviceImpl;

import com.cqut.dao.BookImageMapper;
import com.cqut.entity.Book;
import com.cqut.entity.BookImage;
import com.cqut.entity.User;
import com.cqut.service.BookImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import static com.cqut.util.SessionUtil.HttpUtil.getProjectBasePath;

@Service
public class BookImageServiceImpl implements BookImageService {

    private static final String IMG_FILE_BASE_PATH = "BOOK_IMG";

    @Autowired
    private BookImageMapper bookImageMapper;


    @Override
    public void insert(MultipartFile[] multipartFiles, Book book, User curUser) throws IOException {
        /*生成文件路径*/
        List<BookImage> bookImageList = new LinkedList<>();
        try {
            for (MultipartFile multipartFile1 : multipartFiles) {
                UUID uuid = UUID.randomUUID();
                System.out.println("图片名 :" + multipartFile1.getOriginalFilename() + " type:" + multipartFile1.getContentType());
                String fileName = multipartFile1.getOriginalFilename();
                String fileType = fileName.substring(fileName.lastIndexOf('.'));
                String filePath = getProjectBasePath() + IMG_FILE_BASE_PATH + File.separator + curUser.getId() + File.separator + uuid + fileType;
                String imgPath = File.separator + IMG_FILE_BASE_PATH + File.separator + curUser.getId() + File.separator + uuid + fileType;

                System.out.println("图片 存储 ：" + " 路径: " + filePath + " 类型:" + fileType + "  文件相对路径 :" + imgPath);
                String baseProjectPath = getProjectBasePath();
                File file = null;
                file = new File(filePath);
                if (!file.getParentFile().isDirectory() || !file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                    file.createNewFile();
                }
                multipartFile1.transferTo(file);
                BookImage bookImage = new BookImage();
                bookImage.setBook(book);
                bookImage.setDirectory(imgPath);
                bookImageList.add(bookImage);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
        bookImageMapper.batchInsert(bookImageList);
    }

    //TODo 如果是破坏性的
    @Override
    public void updateImages(MultipartFile[] files, Book book, User curUser, List<BookImage> oldBookImageList) throws IOException {
        /*先删除缘原来的oldBookImageList中的数据库信息及文件*/
        bookImageMapper.batchDelete(oldBookImageList);
        deleteLocalImg(oldBookImageList, curUser);
        /*插入新的图片到文件中，并且将文件信息持久化到数据库中*/
        this.insert(files, book, curUser);
    }

    @Override
    public void updateImages(MultipartFile[] files, Book book, User curUser) throws IOException {
        List<Long> list = new LinkedList<>();
        list.add(book.getId());
        List<BookImage> bookImageList = bookImageMapper.selectByBidList(list);
        deleteLocalImg(bookImageList, curUser);
        BookImage bookImage = new BookImage();
        bookImage.setBook(book);
        bookImageMapper.deleteByBid(bookImage);
        this.insert(files, book, curUser);

    }

    ;

    @Override
    public void deleteOneImage(User curUser, List<BookImage> bookImageList) throws IOException {
        deleteLocalImg(bookImageList, curUser);
        bookImageMapper.batchDelete(bookImageList);
    }

    @Override
    public void deleteImageList(List<Long> bookIdList, User curUser) throws IOException {
        List<BookImage> bookImageList = bookImageMapper.selectByBidList(bookIdList);
        deleteLocalImg(bookImageList, curUser);
        bookImageMapper.batchDelete(bookImageList);
    }

    ;

    //TODO NIO  File DELETE
    private void deleteLocalImg(List<BookImage> oldBookImageList, User user) throws IOException {
        String imgListPath = getProjectBasePath() + IMG_FILE_BASE_PATH + File.separator + user.getId() + File.separator;
        if (Files.isExecutable(Paths.get(imgListPath))) {
            FileDeleteVisitor fileDeleteVisitor = new FileDeleteVisitor(oldBookImageList);
            Files.walkFileTree(Paths.get(imgListPath), fileDeleteVisitor);
        }
    }


    //fixme maybe wrong
    public class FileDeleteVisitor extends SimpleFileVisitor<Path> {
        private List<BookImage> oldBookImageList;

        public FileDeleteVisitor(List<BookImage> oldBookImageList) {
            this.oldBookImageList = oldBookImageList;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

            /*
            List<BookImage> bookImages = oldBookImageList.stream().filter(bookImage ->
                    bookImage.getDirectory().substring(bookImage.getDirectory().lastIndexOf("/")).compareToIgnoreCase(file.getFileName().toString()) == 0
            ).collect(Collectors.toList());
            */

            for (BookImage bookImage : oldBookImageList) {
                System.out.println("文件准备删除： 文件路径:" + bookImage.getDirectory());

                if (bookImage.getDirectory().substring(bookImage.getDirectory().lastIndexOf("\\") + 1).compareToIgnoreCase(file.getFileName().toString()) == 0) {
                    System.out.println("文件删除： 文件路径:" + file.toString());
                    if (Files.exists(file, new LinkOption[]{}))
                        Files.delete(file);
                }
            }
            return super.visitFile(file, attrs);
        }
    }
}
