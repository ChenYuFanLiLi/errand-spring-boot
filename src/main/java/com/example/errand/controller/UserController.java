package com.example.errand.controller;


import com.example.errand.common.CommonResult;
import com.example.errand.dto.LoginDto;
import com.example.errand.dto.RegisterDto;
import com.example.errand.entity.User;
import com.example.errand.jwt.JwtUtils;
import com.example.errand.service.UserService;
import com.example.errand.util.MD5Utils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
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
        //加密密码

        registerDto.setPassword(new SimpleHash("SHA-1", registerDto.getPassword(), registerDto.getUsername()).toString());
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

        String ans=null;
        //获取当前用户主体
        Subject subject= SecurityUtils.getSubject();
        //将用户名和密码封装成UsernamePasswordToken对象
        UsernamePasswordToken token = new UsernamePasswordToken(loginDto.getUsername(),loginDto.getPassword());
        System.out.println("封装usernamepasswordtoken对象成功/n");
        try {
            subject.login(token);
            System.out.println("登录成功subject.longin(token)");
            ans="登录成功";
            //登录成功签发jwttoken
            String jwtToken= JwtUtils.sign(loginDto.getUsername(),JwtUtils.SECRET);
            //添加请求头
//            ((HttpServletResponse) response).setHeader(JwtUtils.AUTH_HEADER, jwtToken);
//            CommonResult result = new CommonResult(token);

            return CommonResult.success(token);
        }catch (UnknownAccountException uae){//账号不存在
            ans="用户名与密码不匹配，请检查后重新输入！";
        }catch (IncorrectCredentialsException ice){//账号与密码不匹配
            ans="用户名与密码不匹配，请检查后重新输入！";
        }catch (LockedAccountException lae){
            ans="该账户已被锁定，如需解锁请联系管理员！";
        }catch (AuthenticationException ae){
            System.out.println(ae);
            ans="登录异常，请联系管理员！";
        }
        return CommonResult.failed(ans);

//        log.info(loginDto.toString());
//        //String ans=userService.login(loginDto);
//        if (ans.equals("用户名密码正确")){
//            System.out.println(MD5Utils.code(loginDto.getPassword()));
//            System.out.println("用户名密码正确登录成功");
//            return CommonResult.success(ans);
//        }


    }
    /**
     * 用户退出
     */
    @GetMapping("/logout")
    public CommonResult logout(){
        //缺一个删除token,看是保存在哪
        return CommonResult.success("退出登录");
    }
}

