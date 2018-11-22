package com.cqut.entity;

public class Category {
    private Long id;
    private String category;

    public Category(Long cid) {
        this.id = cid;
    }

    public Category() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
