package com.evol.multi;

public enum DataSourceKeyEnum {

    DATA_SOURCE_KEY_BUSINESS("business", "business业务数据库的mysql数据源"),
    DATA_SOURCE_KEY_USER("business", "user用户数据库的mysql数据源"),
    DATA_SOURCE_KEY_ORDER("business", "order订单数据库的mysql数据源");

    private String key;

    private String desc;

     DataSourceKeyEnum(String key, String desc){
        this.key = key;
        this.desc = desc;
    }



}
