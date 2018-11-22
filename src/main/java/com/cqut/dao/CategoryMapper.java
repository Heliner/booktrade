package com.cqut.dao;

import com.cqut.entity.Category;

import java.util.List;
import java.util.Map;

public interface CategoryMapper {
    public List<Category> categoryList();

    public Category getCategoryById(Long id);
}
