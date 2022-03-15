package com.example.errand.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

/**
 * @author : 陈宇凡
 * @date : 2022/3/14
 **/
@Mapper
public interface PermMapper {
    @Select("select perm from errand_user_perm where username=#{username}")
    Set<String> getPermByUsername(String username);
}
