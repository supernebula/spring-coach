package com.evol.interceptor;

import lombok.Data;

@Data
public class TokenRuntimeException extends RuntimeException {private Integer code = 401;
        private String msg; public TokenRuntimeException(String msg) { this.msg = msg;
    }
}
