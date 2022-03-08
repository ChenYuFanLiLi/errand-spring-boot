package com.example.errand.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author 陈宇凡
 * @since 2021-11-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("errand_user")
public class User implements Serializable {

    private static final long serialVersionUID=1L;
    //该注解为主键自增
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String userId;

    private String username;

    private String password;

    private String email;

    private Integer phoneNumber;


}
