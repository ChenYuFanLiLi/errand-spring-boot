package com.example.errand.service.impl;

import com.example.errand.entity.Role;
import com.example.errand.mapper.RoleMapper;
import com.example.errand.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author : 陈宇凡
 * @date : 2022/3/13
 **/
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleMapper roleMapper;
    @Override
    public Set<String> getRoleByUsername(String username) {
        Set<String> role= roleMapper.getRoleByUsername(username);
        return role;
    }
}
