package com.example.bookstore.mapper;

import com.example.bookstore.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


@Mapper
public interface UserMapper {
    @Select("select * from user where name=#{username}")
    User findByUserName(String username);

    @Insert("insert into user(name,password) values (#{username},#{password})")
    void add(String username, String password);
    @Update("update user set name=#{name} where userId=#{userId}")
    void update(User user);
    @Update("update user set avatar=#{avatar} where userId=#{id}")
    void updateAvatar(String avatar, Integer id);
    @Update("update user set password=#{newPwd} where userId=#{id}")
    void updatePwd(String newPwd,Integer id);
}
