package com.example.errand.common;

import lombok.Getter;

/**
 * 公共返回类
 * @author : 陈宇凡
 * @date : 2021/11/27
 **/
@Getter
public class CommonResult {
    private Integer code;
    private String message;
    private Object obj;

    private CommonResult(Integer code, String message,Object obj){
        this.code=code;
        this.message=message;
        this.obj=obj;

    }
    public static CommonResult nohandler() {
        return new CommonResult(ResultCode.NOHANDLER.getCode(), ResultCode.NOHANDLER.getMessage(),null);
    }
    public static CommonResult success(Object data) {
        return new CommonResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(),data);
    }
    public static CommonResult failed() {
        return new CommonResult(ResultCode.FAILED.getCode(), ResultCode.FAILED.getMessage(),null);
    }
    public static CommonResult failed(String message) {
        return new CommonResult(ResultCode.FAILED.getCode(),message,null);
    }
    public static CommonResult notoken() {
        return new CommonResult(ResultCode.NOTOKEN.getCode(), ResultCode.NOTOKEN.getMessage(),null);
    }
    public static CommonResult nopremiss() {
        return new CommonResult(ResultCode.NOPERMISS.getCode(), ResultCode.NOPERMISS.getMessage(),null);
    }
}
