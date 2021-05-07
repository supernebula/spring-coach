package com.evol.domain;

/**
 * 用户余额变动类型枚举
 */
public enum UserBalanceChangeType {

    ADD_MONEY(0, "增加余额"),
    REDUCE_MONEY(1, "减少余额");


    private Integer code;

    private String desc;

    UserBalanceChangeType(Integer code, String desc){
        this.code = code;
        this.desc = desc;
    }

}
