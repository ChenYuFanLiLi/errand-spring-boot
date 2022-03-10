package com.example.errand.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.errand.dto.OrderDto;
import com.example.errand.entity.Order;



/**
 * @author : 陈宇凡
 * @date : 2022/3/10
 **/
public interface OrderService extends IService<Order> {
    String addOrder(OrderDto orderDto);


}
