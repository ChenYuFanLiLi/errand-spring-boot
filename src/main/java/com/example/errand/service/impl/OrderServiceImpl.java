package com.example.errand.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.errand.dto.OrderDto;
import com.example.errand.entity.Order;
import com.example.errand.mapper.OrderMapper;
import com.example.errand.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static com.example.errand.util.autoUtils.autoNumber;
import static com.example.errand.util.autoUtils.getTime;

/**
 * @author : 陈宇凡
 * @date : 2022/3/10
 **/
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
    @Autowired
    OrderMapper orderMapper;

    @Override
    public String addOrder(OrderDto orderDto) {
        Order order = new Order();

        order.setOrderId(autoNumber());

        order.setOrderAmount(orderDto.getOrderAmount());
        order.setOrderName(orderDto.getOrderName());
        order.setOrderStatus(orderDto.getOrderStatus());
        order.setOrderDetailsId(orderDto.getOrderDetailsId());

        order.setOrderCreatedTime(getTime());
        //order.setOrderCompletionTime(orderDto.getOrderCompletionTime());
        System.out.println(order);
        orderMapper.insert(order);
        return "OK";
    }
}
