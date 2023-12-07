package com.example.bookstore.controller;

import com.example.bookstore.pojo.Article;
import com.example.bookstore.pojo.PageBean;
import com.example.bookstore.pojo.Result;
import com.example.bookstore.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @PostMapping
    public Result<Article> add(@RequestBody @Validated Article article){
        articleService.add(article);
        return Result.success();

    }

    @GetMapping
    public Result<PageBean<Article>> list(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) String state
    ){
        PageBean<Article> articles=articleService.list(pageNum,pageSize,categoryId,state);
        return Result.success(articles);
    }
}
