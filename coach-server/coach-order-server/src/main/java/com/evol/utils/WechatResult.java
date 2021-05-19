package com.evol.utils;

import lombok.Data;

@Data
public class WechatResult {
    public static final int NEWSMSG = 1; // 图文消息

    private boolean success;

    private int type;

    private Object object;

    private String msg;

    public boolean isSuccess() {
        return success;
    }
}
