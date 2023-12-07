package com.example.bookstore.controller;

import com.example.bookstore.pojo.Category;
import com.example.bookstore.pojo.Result;
import com.example.bookstore.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public Result add(@RequestBody @Validated(Category.Add.class) Category category){
        categoryService.add(category);
        return Result.success();
    }
    @GetMapping
    public Result<List<Category>> list(){
        List<Category> categories=categoryService.list();
        return Result.success(categories);
    }
    @GetMapping("/detail")
    public Result<Category> detail(Integer id){
        Category category=categoryService.detail(id);
        return Result.success(category);
    }
    @PutMapping
    public Result update(@RequestBody @Validated(Category.Update.class) Category category){
        categoryService.update(category);
        return Result.success();
    }

    @DeleteMapping
    public Result delete(@RequestParam String id){
        categoryService.delete(id);
        return Result.success();
    }
}
