package com.example.errand.dto;

import lombok.Data;
import lombok.ToString;

/**
 * @author : 陈宇凡
 * @date : 2021/11/27
 **/
@Data
@ToString
public class RegisterDto {
    private String username;//用户名

    private String userId;//用户id
    private String password;//密码
    private String secondPassword;//二次密码
    private String email;
    private Integer phoneNuber;
}
