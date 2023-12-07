package com.example.bookstore.service.impl;

import com.example.bookstore.mapper.ArticleMapper;
import com.example.bookstore.pojo.Article;
import com.example.bookstore.pojo.PageBean;
import com.example.bookstore.service.ArticleService;
import com.example.bookstore.utils.ThreadLocalUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import jakarta.validation.groups.ConvertGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    @Override
    public void add(Article article) {
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());

        Map<String,Object> map= ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        article.setCreateUser(id);

        articleMapper.add(article);
    }

    @Override
    public PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
        //1.创建pagebean对象
        PageBean<Article> articles=new PageBean<>();

        //2.开启分页查询pageHelper
        PageHelper.startPage(pageNum,pageSize);

        //3.调用mapper
        Map<String,Object> map=ThreadLocalUtil.get();
        Integer id=(Integer) map.get("id");
        List<Article> articleList=articleMapper.list(categoryId,state,id);
        //Page中提供了方法，可以获取pagehelper分页查询后得到的总记录数和当前页面数据
        Page<Article> p=(Page<Article>) articleList;
        //把数据填充到pageBean对象中
        articles.setTotal(p.getTotal());
        articles.setItems(p.getResult());
        return articles;
    }
}
