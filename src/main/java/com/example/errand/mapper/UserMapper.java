package com.example.errand.mapper;

import com.example.errand.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 陈宇凡
 * @since 2021-11-26
 */
@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {
    @Select("select password from errand_user where username =#{username}")
    String selectPdByUsername(String username);
    @Select("select * from errand_user where username=#{username}")
    User getUserByUsername(String username);
}
