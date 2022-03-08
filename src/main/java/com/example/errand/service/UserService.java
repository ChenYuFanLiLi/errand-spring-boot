package com.example.errand.service;

import com.example.errand.dto.LoginDto;
import com.example.errand.dto.RegisterDto;
import com.example.errand.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 陈宇凡
 * @since 2021-11-26
 */
public interface UserService extends IService<User> {
    String register (RegisterDto registerDto);

    String login(LoginDto loginDto);
}
