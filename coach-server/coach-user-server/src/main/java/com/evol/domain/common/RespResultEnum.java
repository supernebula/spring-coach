package com.evol.domain.common;

/**
 * 错误码定义范围  6000-7000
 */
public enum RespResultEnum {

    SUCCESS(200, "成功"),
    VISIT_WEIXIN_API_ERROR(7020, "调用微信获取用户openid失败: "),
    WEIXIN_CODE_ERROR(7022, "invalid code: Code无效错误 "),
    CODE_NOT_EMPTY_ERROR(7023, "code not empty!!! "),
    WEIXIN_USER_INFO_ERROR(7025, "invalid openid: openid无效或为空");

    private Integer code;
    private String message;

    RespResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
