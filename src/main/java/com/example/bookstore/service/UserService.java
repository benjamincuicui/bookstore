package com.example.bookstore.service;

import com.example.bookstore.pojo.User;

public interface UserService {
    public User findByUserName(String username);
    public void register(String username,String password);

    void update(User user);

    void updateAvatar(String avatar);

    void updatePwd(String newPwd);
}
