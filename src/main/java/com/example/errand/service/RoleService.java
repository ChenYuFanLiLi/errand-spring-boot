package com.example.errand.service;

import com.example.errand.entity.Role;

import java.util.List;
import java.util.Set;

/**
 * @author : 陈宇凡
 * @date : 2022/3/13
 **/
public interface RoleService {
    Set<String> getRoleByUsername(String username);
}
