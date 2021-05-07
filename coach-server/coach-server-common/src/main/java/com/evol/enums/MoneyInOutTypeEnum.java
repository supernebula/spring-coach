package com.evol.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MoneyInOutTypeEnum {
    //
    RECHARGE(0, "充值"),
    CONSUME(1, "消费"),
    REFUND(2, "退款");

    private Integer code;

    private String description;
}

