package com.example.bookstore.controller;

import com.example.bookstore.pojo.Result;
import com.example.bookstore.pojo.User;
import com.example.bookstore.service.UserService;
import com.example.bookstore.utils.JwtUtil;
import com.example.bookstore.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Validated
@RestController

public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{3,10}$") String username, @Pattern(regexp = "^\\S{3,10}$") String password){
        User user=userService.findByUserName(username);
        if(user==null){
            userService.register(username,password);
            return Result.success();
        }else{
            return Result.error("用户已注册！");
        }
    }
    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp = "^\\S{3,10}$") String username, @Pattern(regexp = "^\\S{3,10}$")String password){
        User user=userService.findByUserName(username);
        if(user==null){
            return Result.error("用户名错误");
        }
        if(password.equals(user.getPassword())){
            Map<String,Object> claims=new HashMap<>();
            claims.put("id",user.getUserId());
            claims.put("username",user.getName());
            String token= JwtUtil.genToken(claims);
            //将token存入redis中
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            operations.set(token,token,12, TimeUnit.HOURS);
            return Result.success(token);
        }
        return Result.error("密码错误");
    }

    @GetMapping("/userInfo")
    public Result<User> userInfo(){
        //根据用户名查询用户
        Map<String,Object> map=ThreadLocalUtil.get();
        String username=(String) map.get("username");

        User user=userService.findByUserName(username);
        return Result.success(user);
    }

    @PutMapping("/update")
    public Result<String> update(@RequestBody @Validated User user){
        userService.update(user);
        return Result.success("更新成功");
    }

    @PatchMapping("/updateAvatar")
    public Result<String> updateAvatar(@RequestParam @URL String avatar){
        userService.updateAvatar(avatar);
        return Result.success("更新头像成功");
    }

    @PatchMapping("/updatePwd")
    public Result<String> updatePwd(@RequestBody Map<String,String> params,@RequestHeader("Authorization") String token){
        //通过map将传过来的json字符逐一获取
        String oldPwd=params.get("old_pwd");
        String newPwd=params.get("new_pwd");
        String rePwd=params.get("re_pwd");
        //数据校验
        if(!StringUtils.hasLength(oldPwd)||!StringUtils.hasLength(newPwd)||!StringUtils.hasLength(rePwd)){
            return Result.error("输入项缺失");
        }

        Map<String,Object> map=ThreadLocalUtil.get();
        String username=(String) map.get("username");
        User user=userService.findByUserName(username);
        if(!user.getPassword().equals(oldPwd)){
            return Result.error("原密码输入错误");
        }

        if(!newPwd.equals(rePwd)){
            return Result.error("两次填写的密码不一致");
        }

        userService.updatePwd(newPwd);

        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.getOperations().delete(token);

        return Result.success("修改成功");


    }
}
