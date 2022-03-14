package com.example.errand.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author : 陈宇凡
 * @date : 2022/3/13
 **/
@Data
@TableName("errand_user_role")
public class Role {
    private String username;
    private String role;
}
