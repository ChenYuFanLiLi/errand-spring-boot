package com.example.errand.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author : 陈宇凡
 * @date : 2022/3/14
 **/
@Data
@TableName("errand_user_perm")
public class Perm {
    private String username;
    private String perm;
}
