package com.example.errand.service.impl;

import com.example.errand.dto.LoginDto;
import com.example.errand.dto.RegisterDto;
import com.example.errand.entity.User;
import com.example.errand.mapper.UserMapper;
import com.example.errand.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.errand.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 陈宇凡
 * @since 2021-11-26
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public String register(RegisterDto registerDto) {
//        String ans=istrue(registerDto);
        String ans="OK";
        if (ans.equals("OK")){
            User user=new User();
            user.setUsername(registerDto.getUsername());
            user.setNickName(registerDto.getNickName());
            user.setEmail(registerDto.getEmail());
            user.setPhoneNumber(registerDto.getPhoneNuber());
            //存入加密后的密码
            user.setPassword(MD5Utils.code(registerDto.getPassword()));

            userMapper.insert(user);//写入数据库
        }
        return ans;
    }



    @Override
    public String login(LoginDto loginDto) {
        //获取数据库中已加密密码
        String password=userMapper.selectPdByUsername(loginDto.getUsername());
        if (password==null){
            return "用户名错误";
        }else if (password.equals(MD5Utils.code((loginDto.getPassword())))){
            //用户输入密码 加密后 与数据库中已加密密码比对
            return "用户名密码正确";
        }
        return "密码错误";

    }

    @Override
    public User getUserByusername(String username) {
        User currentUser =userMapper.getUserByUsername(username);
        return currentUser;
    }

    private String istrue(RegisterDto loginDto) {
        if (loginDto.getUsername()==null||loginDto.getUsername()==""){
            return "用户名不能为空";
        }
        return "OK";
    }
}
