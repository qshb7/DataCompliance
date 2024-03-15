package com.example.datacompliance.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author zhengzh1
 * @date 2024/3/2 13:38
 */
@Data
public class User {
    @NotNull
    private Integer id;//主键ID
    private String username;//用户名
    @JsonIgnore//让springmvc把当前对象转换为json字符串的时候，忽略password，最终的json字符串中就没有password这个字段了
    private String password;//密码

    @NotEmpty
    @Email
    private String email;//邮箱
    @NotEmpty
    private String phoneNumber; // 电话号码

    private String userPic;//用户头像地址
    private LocalDateTime createTime;//创建时间
    private LocalDateTime updateTime;//更新时间
}
