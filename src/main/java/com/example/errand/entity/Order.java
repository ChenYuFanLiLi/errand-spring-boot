package com.example.errand.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author : 陈宇凡
 * @date : 2022/3/10
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("errand_order")
public class Order implements Serializable {
    private static final long serialVersionUID=1L;
    //自增主键
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String orderId;
    private String createUsername;
    private String errandUsername;
    private String orderName;
    private String orderStatus;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date orderCreatedTime;
    private Date orderCompletionTime;
    private Integer orderAmount;
    private Integer orderDetailsId;

}
