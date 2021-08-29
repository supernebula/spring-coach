package com.evol.exception;

import com.evol.enums.ApiResponseEnum;
import com.evol.interceptor.TokenRuntimeException;
import com.evol.web.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class SysRuntimeExceptionHandler {

    @ExceptionHandler(TokenRuntimeException.class)
    public ApiResponse tokenRuntimeException(TokenRuntimeException ex) {
        log.error(ex.getMsg(), ex);
        return ApiResponse.fail(ex.getCode(), ex.getMsg());
    }

    @ExceptionHandler(Exception.class)
    public ApiResponse handlerException(Exception ex) {
        log.error(ex.getMessage(), ex);
        return ApiResponse.fail(ApiResponseEnum.USER_DEFINED_ERROR, ex.getMessage());
    }
}
