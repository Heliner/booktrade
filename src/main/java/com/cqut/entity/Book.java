package com.cqut.entity;

import java.util.Date;
import java.util.List;

public class Book {
    private Long id;
    private String name;
    private Integer bookType;
    private double price;
    private double originalPrice;
    private User user;
    private String author;
    private String press;
    private String version;
    private Double degree;
    private String publishDate;

    private String description;

    private Date shelvesDate;
    private Category category;
    private List<BookImage> bookImageList;
    private Long favoritesCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public Integer getBookType() {
        return bookType;
    }

    public void setBookType(Integer bookType) {
        this.bookType = bookType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Double getDegree() {
        return degree;
    }

    public void setDegree(Double degree) {
        this.degree = degree;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getShelvesDate() {
        return shelvesDate;
    }

    public void setShelvesDate(Date shelvesDate) {
        this.shelvesDate = shelvesDate;
    }


    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<BookImage> getBookImageList() {
        return bookImageList;
    }

    public void setBookImageList(List<BookImage> bookImageList) {
        this.bookImageList = bookImageList;
    }

    public Long getFavoritesCount() {
        return favoritesCount;
    }

    public void setFavoritesCount(Long favoritesCount) {
        this.favoritesCount = favoritesCount;
    }
}
