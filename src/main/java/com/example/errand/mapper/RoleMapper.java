package com.example.errand.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.errand.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @author : 陈宇凡
 * @date : 2022/3/13
 **/
@Mapper
@Repository
public interface RoleMapper extends BaseMapper<Role> {
    @Select("select role from errand_user_role where username =#{username}")
    Set<String> getRoleByUsername(String username);
}
