package com.example.bookstore.service.impl;


import com.example.bookstore.mapper.UserMapper;
import com.example.bookstore.pojo.User;
import com.example.bookstore.service.UserService;
import com.example.bookstore.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public User findByUserName(String username) {
        User user =userMapper.findByUserName(username);
        return user;
    }

    @Override
    public void register(String username, String password) {
        userMapper.add(username,password);

    }

    @Override
    public void update(User user) {
        userMapper.update(user);

    }

    @Override
    public void updateAvatar(String avatar) {
        Map<String,Object> map=ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        userMapper.updateAvatar(avatar,id);
    }

    @Override
    public void updatePwd(String newPwd) {
        Map<String,Object> map=ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        userMapper.updatePwd(newPwd,id);
    }
}
