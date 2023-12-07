package com.example.bookstore.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class User {
    @NotNull
    private Integer userId;

    @NotEmpty
    @Pattern(regexp = "^\\S{3,10}$")
    private String name;

    @JsonIgnore//让springmvc把当前对象转换成json字符串的时候，忽略password，最终的json字符串中就没有password这个属性了
    @Pattern(regexp = "^\\S{3,10}$")
    private String password;

    private String avatar;

}
