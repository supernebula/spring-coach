package com.evol.enums;

import lombok.Getter;

@Getter
public enum UserType {

    USER(0, "C端用户"),
    STAFF(1, "员工账号");

    private Integer code;

    private String desc;

    UserType(Integer code, String desc){
        this.code = code;
        this.desc = desc;
    }

}
