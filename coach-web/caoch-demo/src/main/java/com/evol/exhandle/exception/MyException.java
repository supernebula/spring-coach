package com.evol.exhandle.exception;

import lombok.Data;

@Data
public class MyException extends RuntimeException{
    private int code;
    private String msg;
    public MyException(String msg){
        super(msg);
        this.code = 500;
        this.msg = msg;
    }
}
