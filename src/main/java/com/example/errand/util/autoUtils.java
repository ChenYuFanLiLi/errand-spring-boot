package com.example.errand.util;
import lombok.SneakyThrows;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 自动生成编号
 * @author : 陈宇凡
 * @date : 2022/3/10
 **/
public class autoUtils {
    public static String autoNumber(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
        int random= (int)(Math.random()*90+10);
        String orderId=sdf.format(new Date())+random;
        return orderId;
    }


    /**
     * 获取当前时间并返回一个Date类型
     */
    @SneakyThrows
    public static Date getTime(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
        String nowTime=sdf.format(date);
        Date time= sdf.parse(nowTime);
        return time;
    }

}
