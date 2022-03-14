package com.example.errand.jwt;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author : 陈宇凡
 * @date : 2022/3/13
 **/
public class JwtToken implements AuthenticationToken {
    private static final long serialVersionUID=1L;
    //加密后的Jwt token 串
    private String token;
    private String userName;

    public JwtToken(String token){
        this.token=token;
        this.userName= JwtUtils.getClaimFiled(token,"username");
    }

    @Override
    public Object getPrincipal(){
        return this.userName;
    }
    @Override
    public Object getCredentials(){
        return token;
    }
}
