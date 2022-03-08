package com.example.errand.dto;

import lombok.Data;
import lombok.ToString;

/**
 * @author : 陈宇凡
 * @date : 2021/11/27
 **/
@Data
@ToString
public class LoginDto {
    private String  userId;//用户账号
    private String password;//密码

}
