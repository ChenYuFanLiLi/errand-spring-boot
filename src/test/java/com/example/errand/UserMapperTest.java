package com.example.errand;

/**
 * @author : 陈宇凡
 * @date : 2021/11/26
 **/

/**
 * 数据库连接测试类
 */

import com.example.errand.dto.RegisterDto;
import com.example.errand.entity.User;
import com.example.errand.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class UserMapperTest {

    @Resource
    UserService userService;

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
}
