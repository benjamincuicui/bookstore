package com.example.bookstore.service;

import com.example.bookstore.pojo.Article;
import com.example.bookstore.pojo.PageBean;

public interface ArticleService {
     void add(Article article);

    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);
}
