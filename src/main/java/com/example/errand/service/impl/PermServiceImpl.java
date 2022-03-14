package com.example.errand.service.impl;

import com.example.errand.mapper.PermMapper;
import com.example.errand.service.PermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author : 陈宇凡
 * @date : 2022/3/14
 **/
@Service
public class PermServiceImpl implements PermService {
    @Autowired
    PermMapper permMapper;
    @Override
    public Set<String> getPermByUsername(String username) {
        Set<String> perm=permMapper.getPermByUsername(username);
        return perm;
    }
}
