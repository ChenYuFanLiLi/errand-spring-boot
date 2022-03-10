package com.example.errand.dto;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @author : 陈宇凡
 * @date : 2022/3/10
 **/
@Data
@ToString
public class OrderDto {
    private Integer id;
    private String orderId;
    private String orderName;
    private String orderStatus;
    private Date orderCreatedTime;
    private Date orderCompletionTime;
    private Integer orderAmount;
    private Integer orderDetailsId;
}
