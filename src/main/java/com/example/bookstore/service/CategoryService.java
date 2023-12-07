package com.example.bookstore.service;

import com.example.bookstore.pojo.Category;

import java.util.List;

public interface CategoryService {
    void add(Category category);

    List<Category> list();

    //根据id获取指定文章分类
    Category detail(Integer id);

    void update(Category category);

    void delete(String id);
}
