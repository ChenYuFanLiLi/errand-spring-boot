package com.example.errand.service;

import java.util.Set;

/**
 * @author : 陈宇凡
 * @date : 2022/3/14
 **/
public interface PermService {
    Set<String> getPermByUsername(String username);
}
