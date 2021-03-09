package com.evol.domain.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespResult<T> {

    private Integer code;
    private String message;
    private T data;

}
