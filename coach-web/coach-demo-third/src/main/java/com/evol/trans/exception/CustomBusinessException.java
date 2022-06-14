package com.evol.trans.exception;

/**
 * 自定义事务异常
 * 如何使用，发生此异常，事务回滚后，可以在全局异常处理中，拦截CustomBusinessException，记录日志，返回自定义结果（错误页面，JSON、XML...等）
 */
public class CustomBusinessException extends RuntimeException {
    private int code;

    private String errMsg;

    public  CustomBusinessException(int code, String msg){
        this.code = code;
        this.errMsg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
