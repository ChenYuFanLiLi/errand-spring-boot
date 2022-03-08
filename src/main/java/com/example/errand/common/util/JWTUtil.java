package com.example.errand.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.shiro.util.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

/**
 * @author : 陈宇凡
 * @date : 2022/3/8
 **/
@Component
public class JWTUtil {
    public static boolean verify(String token,String username,String secret){
        try {
            /**
             * Algorithm.HMAC256(uerPassword)  使用HMAC256加密算法，生成签名。
             * 具体
             * <br>  public static String sign(String username, Integer role, Integer userId, String secret, long expireTimeInMilliSeconds) {
             *     try {
             * //设置过期时间：获取当前时间+过期时间（毫秒）
             *       Date date = new Date(System.currentTimeMillis() + expireTimeInMilliSeconds);
             * //设置签名的加密算法：HMAC256
             *       Algorithm algorithm = Algorithm.HMAC256(secret);
             *       // 附带username信息
             *       return JWT.create().withClaim("username", username).withClaim("role", role).withClaim("uerId", userId).withExpiresAt(date).sign(algorithm);
             *     } catch (UnsupportedEncodingException e) {
             *       return null;
             *     }
             *   }<br><br>以上是生成token 的方法
             */
            Algorithm algorithm=Algorithm.HMAC256(secret);
            /**
             * 将签名添加到verifier
             */
            JWTVerifier verifier= JWT.require(algorithm)
                    .withClaim("username",username)
                    .build();
            verifier.verify(token);
            return true;
        } catch (Exception e){
            log.info("token is invalid{}",e.getMessage());
            return false;
        }
    }
    public static String getUsername(HttpServletRequest request){
        //获取token
        String token=request.getHeader("Authorization");
        //uuofferUtil!!!!是个啥
        return getUsername(UofferUtil.decryptToken(token));
    }
    /**
     * 从token中获取用户名
     * @return token
     */
    public static String getUsername(String token){
        try {
            DecodedJWT jwt=JWT.decode(token);
            return jwt.getClaim("username",asString());
        }catch (JWTDecodeException e){
            log.error("error:{}",e.getMessage());
            return null;
        }
    }
    public static Integer getUserId(HttpServletRequest request){
        //获取token
        String token=request.getHeader("Authorization");
        //uofferUtil!!!!是个啥
        return getUserId(UofferUtil.decryptToken(token));
    }
    /**
     * 从token中获取用户id
     * @return token
     */
    public static Integer getUserId(String token){
        try {
            DecodedJWT jwt=JWT.decode(token);
            return Integer.valueOf(jwt.getSubject());
        }catch (JWTDecodeException e){
            log.error("error:{}",e.getMessage());
            return null;
        }
    }
    /**
     * 生成token
     * @param username 用户名
     * @param secret 用户密码
     * @return token
     */
    public static String sign(String username,String secret,Integer userId){
        try {
            Map<String,Object> map=new HashMap<>();
            map.put("alg","HS256");
            map.put("typ","jwt");
            username= StringUtils.lowerCase(username);
            Algorithm algorithm=Algorithm.HMAC256(secret);
            return JWT.create()
                    .withHeader(map)
                    .withClaim("username",username)
                    .withSubject(String.valueOf(userId))
                    .withIssuedAt(new Date())
                    .withExpiresAt(date)
                    .sign(algorithm);
        }catch (Exception e){
            log.error("error:{}",e);
            return null;
        }
    }
}
