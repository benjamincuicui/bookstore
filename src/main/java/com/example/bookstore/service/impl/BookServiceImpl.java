package com.example.bookstore.service.impl;

import com.example.bookstore.mapper.BookMapper;
import com.example.bookstore.pojo.Book;
import com.example.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;
    @Override
    public List<Book> findAll() {
        List<Book> books=bookMapper.findAll();
        return books;
    }
}
