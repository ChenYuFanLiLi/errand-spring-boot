package com.example.errand.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.errand.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : 陈宇凡
 * @date : 2022/3/10
 **/
@Mapper
@Repository
public interface OrderMapper extends BaseMapper<Order> {
    @Select("select * from errand_order where order_id=#{orderId}")
    List<Order> listOrderByOrderId(String orderId);
}
