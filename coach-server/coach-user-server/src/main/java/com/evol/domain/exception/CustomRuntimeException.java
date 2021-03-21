package com.evol.domain.exception;

import com.evol.constants.enums.ApiResponseEnum;
import lombok.Data;

@Data
public class CustomRuntimeException extends RuntimeException {
    private Integer code;
    private String errMsg;

    public CustomRuntimeException(ApiResponseEnum responseEnum) {
        super(responseEnum.getDescription());
        this.code = responseEnum.getCode();
        this.errMsg = responseEnum.getDescription();
    }

    public CustomRuntimeException(Integer code, String errMsg) {
        super(errMsg);
        this.code = code;
        this.errMsg = errMsg;
    }
}
