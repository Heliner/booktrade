import com.cqut.entity.BookImage;
import com.cqut.entity.User;
import com.cqut.serviceImpl.BookImageServiceImpl;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class FileWalker {


    @Test
    public void fileTest() throws IOException {
        String filepath = "F:\\支持文件\\day28_struts2_day3\\BookTrade - 副本\\out\\artifacts\\BookTrade_Web_exploded\\BOOK_IMG\\1\\".trim();
        File file = new File(filepath);
        if (file.isDirectory() || !file.exists())
            file.mkdirs();
        filepath = "F:\\支持文件\\day28_struts2_day3\\BookTrade - 副本\\out\\artifacts\\BookTrade_Web_exploded\\BOOK_IMG\\1\\02e24faa-847f-4154-8bfc-59a5448a5237.jpg";
        System.out.println("\\".length());
        System.out.println("\\".lastIndexOf("\\"));
        System.out.println(filepath);
        System.out.println(filepath.substring(filepath.lastIndexOf('\\') + 1));
    }

    @Test
    public void fileNameSplitTest() throws IOException {
        String FileNmae = "my.sql";
        System.out.println(FileNmae.lastIndexOf('.'));
        System.out.println(FileNmae.substring(FileNmae.lastIndexOf('.')));
        deleteLocalImg(null);
    }

    //TODO NIO  File DELETE
    private void deleteLocalImg(User user) throws IOException {
        String IMG_FILE_BASE_PATH = "F:\\支持文件\\img\\";
        BookImage bookImage = new BookImage();
        bookImage.setDirectory("3.tif");
        List<BookImage> oldBookImageList = new LinkedList<>();
        oldBookImageList.add(bookImage);
        bookImage.setDirectory("3.jpg");
        oldBookImageList.add(bookImage);
        for (BookImage bookImage1 : oldBookImageList) {
            System.out.println("bookimage" + bookImage.getDirectory());
        }
        if (Files.isExecutable(Paths.get(IMG_FILE_BASE_PATH))) {

            FileDeleteVisitor fileDeleteVisitor = new FileDeleteVisitor(oldBookImageList);
            Files.walkFileTree(Paths.get(IMG_FILE_BASE_PATH), fileDeleteVisitor);
        }
    }


    class FileDeleteVisitor extends SimpleFileVisitor<Path> {
        private List<BookImage> oldBookImageList;

        public FileDeleteVisitor(List<BookImage> oldBookImageList) {
            this.oldBookImageList = oldBookImageList;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
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
