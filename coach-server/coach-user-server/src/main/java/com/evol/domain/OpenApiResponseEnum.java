package com.evol.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author czq
 * @date 2020/05/18
 */

@AllArgsConstructor
@Getter
public enum OpenApiResponseEnum {

    SUCCESS(0, "网关响应成功"),
    SERVER_INTERNAL_ERROR(500, "网关:系统出错"),
    SIGN_ERROR(10001, "网关:请求参数sign签名错误"),
    APP_KEY_INVALID(10002, "网关:请求参数app_key无效"),
    AUTH_CODE_ERROR(10003, "网关:请求参数auth_code无效"),
    ACCESS_TOKEN_EXPIRED(10004, "网关:请求参数token过期"),
    ACCESS_TOKEN_INVALID(10005, "网关:请求参数token无效"),
    TIME_ERROR(10006, "网关:时间戳无效"),
    REFRESH_TOKEN_EXPIRED(10007, "网关:请求参数refresh_token过期"),
    REFRESH_TOKEN_INVALID(10008, "网关:请求参数refresh_token无效"),
    OAUTH_GRANT_TYPE_INVALID(10009, "网关:请求参数无效：grant_type无效"),
    //    AUTH_CODE_ERROR(3, "Auth授权码错误"),
    NO_RECORD(20004, "网关:错误的请求，没有记录"),
    MISS_SIGN(20005, "网关:请求缺少参数sign"),
    MiSS_PARAM(20006, "网关:请求缺少参数"),
    TOKEN_ERROR(20007, "网关:请求参数错误，无效的access_token或未曾获取过Token");

    private Integer code;

    private String description;


}
