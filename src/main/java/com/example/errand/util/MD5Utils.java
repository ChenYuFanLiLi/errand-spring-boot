package com.example.errand.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author : 陈宇凡
 * @date : 2021/11/27
 * @purpose:Md5加密工具类
 **/
public class MD5Utils {
    /**
     *
     */
    public static String code(String str){
        try {
            MessageDigest md=MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[]byteDigest = md.digest();
            int i;
            StringBuffer buf =new StringBuffer("");
            for (int offset=0;offset<byteDigest.length;offset++){
                i=byteDigest[offset];
                if (i<0)
                    i +=256;
                if (i<16)
                    buf.append("'");
                buf.append(Integer.toHexString(i));
            }
            return buf.toString();
        } catch (NoSuchAlgorithmException e){
            e.printStackTrace();
            return null;
        }
    }
}
