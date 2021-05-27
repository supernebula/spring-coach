package com.evol.contancts.enums;

import lombok.Getter;

@Getter
public enum NetOrderStatusEnum {
    UNPAID(0, "未支付"),
    PAID(1, "已支付"),
    REFUNDED(2, "已经退款");

    private Integer code;
    private String desc;
    NetOrderStatusEnum(Integer code, String desc){
        this.code = code;
        this.desc = desc;
    }
}
