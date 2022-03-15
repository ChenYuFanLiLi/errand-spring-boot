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
import com.example.errand.service.RoleService;
import com.example.errand.service.UserService;
import com.example.errand.service.OrderService;
import com.mysql.cj.result.SqlDateValueFactory;
import lombok.Data;
import lombok.SneakyThrows;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

@SpringBootTest
public class UserMapperTest {

    @Resource
    UserService userService;
    @Resource
    OrderService orderService;
    @Autowired
    RoleService roleService;

    @Test
    public void test1(){
        User user1 = new User();
        user1=userService.getUserByusername("admin");
        System.out.println(user1);

//        List<User> userList=userService.list();
//        for (User user : userList){
//            System.out.println(user);
//        }
    }

    @Test
    public void test2(){
        RegisterDto registerDto =new RegisterDto();
        registerDto.setPassword("123");
        registerDto.setUsername("adf");
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
//        Date date = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat();
//        String nowTime=sdf.format(date);
//        Date time= sdf.parse(nowTime);
//        OrderDto orderDto = new OrderDto();
//        orderDto.setOrderCreatedTime(time);
//        System.out.println(orderDto.getOrderCreatedTime());
//        Order order = new Order();
//        order.setOrderCreatedTime(orderDto.getOrderCreatedTime());
//        System.out.println("order-------------------------------");
//        System.out.println(order.getOrderCreatedTime());
//        orderMapper.insert(order);

    }
    @Test
    public void ha(){
        User user=new User();
        user.setPassword("adf");
        user.setUsername("adf");
        //加密密码
        String password = new SimpleHash("MD5", user.getPassword(), user.getUsername(), 1024).toString();
        System.out.println(password);
    }
    @Test
    public void testLogin(){
//        User user=new User();
//        user.setPassword("adf");
//        user.setUsername("adf");
//        Subject subject= SecurityUtils.getSubject();
//        UsernamePasswordToken token=new UsernamePasswordToken(user.getUsername(), user.getPassword());
//        subject.login(token);
        String password="qwe";
        String hashPassword=new SimpleHash("SHA-1",password,password,1).toString();
        SimpleHash simpleHash = new SimpleHash("SHA-1",password,password,1);
        System.out.println(simpleHash);
        System.out.println(hashPassword);


    }
    @Test
    public void testgetrole(){
        Set<String> role=roleService.getRoleByUsername("qwe");
        System.out.println(role);
    }
}

