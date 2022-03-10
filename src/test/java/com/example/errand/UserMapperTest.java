package com.example.errand;

/**
 * @author : 陈宇凡
 * @date : 2021/11/26
 **/

/**
 * 数据库连接测试类
 */

import com.example.errand.dto.OrderDto;
import com.example.errand.dto.RegisterDto;
import com.example.errand.entity.Order;
import com.example.errand.entity.User;
import com.example.errand.mapper.OrderMapper;
import com.example.errand.service.UserService;
import com.example.errand.service.OrderService;
import com.mysql.cj.result.SqlDateValueFactory;
import lombok.Data;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class UserMapperTest {

    @Resource
    UserService userService;
    @Resource
    OrderService orderService;

    @Test
    public void test1(){
        List<User> userList=userService.list();
        for (User user : userList){
            System.out.println(user);
        }
    }

    @Test
    public void test2(){
        RegisterDto registerDto =new RegisterDto();
        registerDto.setPassword("123");
        registerDto.setUsername("adf");
        registerDto.setUserId("123");
        String obj= userService.register(registerDto);

    }
    @SneakyThrows
    @Test
    public void test3(){

//        Date date = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat();
//        String nowTime=sdf.format(date);
//        Date time= sdf.parse(nowTime);
        OrderDto orderDto=new OrderDto();
//        orderDto.setId(1);
//        orderDto.setOrderId(2);
        orderDto.setOrderName("123456");
//        orderDto.setOrderCreatedTime(time);
//        System.out.println(orderDto);
//        System.out.println("-------------------------------------");
        orderService.addOrder(orderDto);
//        List<Order> orderList= orderService.list();
//        for (Order order:orderList){
//            System.out.println(order);
//        }
    }

    @Autowired
    OrderMapper orderMapper;

    @SneakyThrows
    @Test
    public void test4(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat();
        String nowTime=sdf.format(date);
        Date time= sdf.parse(nowTime);
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderCreatedTime(time);
        System.out.println(orderDto.getOrderCreatedTime());
        Order order = new Order();
        order.setOrderCreatedTime(orderDto.getOrderCreatedTime());
        System.out.println("order-------------------------------");
        System.out.println(order.getOrderCreatedTime());
        orderMapper.insert(order);

    }
}

