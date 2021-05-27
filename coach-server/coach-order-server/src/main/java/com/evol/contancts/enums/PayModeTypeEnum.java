package com.evol.contancts.enums;

import lombok.Getter;

@Getter
public enum PayModeTypeEnum {

    NONE(0, "无"),
    BALANCE(1, "余额"),
    WECHAT(2, "微信"),
    ALIPAY(3, "支付宝");

    private Integer code;
    private String desc;

    PayModeTypeEnum(Integer code, String desc){
        this.code = code;
        this.desc = desc;
    }
}
