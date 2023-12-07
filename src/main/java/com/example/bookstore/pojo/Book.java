package com.example.bookstore.pojo;

import lombok.Data;

@Data
public class Book {
    private Integer bookId;
    private String name;
    private String cover;
    private String publisher;
    private String price;
}
