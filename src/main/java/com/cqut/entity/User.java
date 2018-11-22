package com.cqut.entity;

import java.util.List;

public class User {
    private Long id;
    private String username;
    private String name;
    private String password;
    private String sex;
    private String tel;
    private String address;
    private String major;
    private String qqAssociated;
    private String campus;
    private String email;
    private List<Book> favoritesList;
    //TODO something
    private List<Book> bookList;

    public Long getId() {

        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getQqAssociated() {
        return qqAssociated;
    }

    public void setQqAssociated(String qqAssociated) {
        this.qqAssociated = qqAssociated;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Book> getFavoritesList() {
        return favoritesList;
    }

    public void setFavoritesList(List<Book> favoritesList) {
        this.favoritesList = favoritesList;

    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {

        this.bookList = bookList;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof User))
            return false;
        return this.getId() == ((User) obj).getId() ? true : false;
    }
}
