package com.cqut.serviceImpl;

import com.cqut.dao.CategoryMapper;
import com.cqut.entity.Category;
import com.cqut.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public List<Category> allCategories() {
        return categoryMapper.categoryList();
    }
}
