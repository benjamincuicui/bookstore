package com.example.bookstore.mapper;

import com.example.bookstore.pojo.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BookMapper {
    @Select("select * from books")
    List<Book> findAll();

}
