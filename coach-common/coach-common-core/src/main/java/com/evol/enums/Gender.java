package com.evol.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Gender {
    //
    NONE(0, "未知"),
    MALE(1, "男"),
    FEMALE(2, "女");

    private Integer code;

    private String desc;
}