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
    private String orderName;//订单名称
    private Integer orderAmount;
    private Integer orderDetailsId;
}
