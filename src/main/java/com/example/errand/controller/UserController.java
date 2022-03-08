package com.example.errand.controller;


import com.example.errand.common.CommonResult;
import com.example.errand.dto.LoginDto;
import com.example.errand.dto.RegisterDto;
import com.example.errand.entity.User;
import com.example.errand.service.UserService;
import com.example.errand.util.MD5Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 陈宇凡
 * @since 2021-11-26
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    /**
     *
     * @param registerDto
     * @return
     */
    @PostMapping("/register")
    public CommonResult register(@RequestBody RegisterDto registerDto){
        String obj =userService.register(registerDto);
        if (obj.equals("注册成功")){
            return CommonResult.success(obj);
        }else {
            return CommonResult.failed(obj);
        }

    }

    /**
     * 用户登录
     * @param loginDto
     * @return
     */
    @PostMapping("/login")
    public CommonResult Login(@RequestBody LoginDto loginDto){
        log.info(loginDto.toString());
        String ans=userService.login(loginDto);
        if (ans.equals("用户名密码正确")){
            System.out.println(MD5Utils.code(loginDto.getPassword()));
            System.out.println("用户名密码正确登录成功");
            return CommonResult.success(ans);
        }
        return CommonResult.failed(ans);

    }
}

