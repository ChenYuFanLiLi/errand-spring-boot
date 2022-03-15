package com.example.errand.controller;

import com.example.errand.common.CommonResult;
import com.example.errand.dto.OrderDto;
import com.example.errand.entity.Order;
import com.example.errand.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : 陈宇凡
 * @date : 2022/3/10
 **/
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {
    @Autowired
    private OrderService orderService;

    /**
     * 添加订单
     * @param orderDto
     * @return
     */
    @RequestMapping("/addOrder")
    public CommonResult addOrder(@RequestBody OrderDto orderDto){
        String obj=orderService.addOrder(orderDto);
        return CommonResult.success("ok");
    }

    /**
     *列出订单
     * @return 订单列表
     */
    @GetMapping("/getOrder")
    @RequiresRoles(value = {"custom"})
    public List<Order> getOrder(){
        System.out.println("成功获取订单");
        List<Order> orderList=orderService.list();
        return orderList;
    }
}
