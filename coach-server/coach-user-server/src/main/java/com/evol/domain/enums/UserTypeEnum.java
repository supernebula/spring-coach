package com.evol.domain.enums;

public enum UserTypeEnum {

    WEI_XIN_USER(1, "普通用户"),
    BACKEND_MANAGER(2, "管理员");

    private int userType;

    private String desc;

    UserTypeEnum(int userType, String desc) {
        this.userType = userType;
        this.desc = desc;
    }

    public int getUserType() {
        return userType;
    }

    public String getDesc() {
        return desc;
    }
}