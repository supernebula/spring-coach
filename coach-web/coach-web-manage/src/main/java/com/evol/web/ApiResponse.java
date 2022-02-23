package com.evol.web;


import com.evol.enums.ApiResponseEnum;
import lombok.Data;

import java.io.Serializable;


@Data
public class ApiResponse<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 网关返回码
     */
    private Integer code=0;

    /**
     * 网关返回码描述
     */
    private String msg="网关响应成功";

    /**
     * 业务返回码
     */
    private Integer subCode=0;

    /**
     * 业务返回码描述
     */
    private String subMsg="成功";

    private T data;

    public ApiResponse(T data) {
        this.data = data;
    }

    public ApiResponse(Integer subCode, String subMsg) {
        this.subCode = subCode;
        this.subMsg = subMsg;
    }

    /**
     * 失败
     * @param code
     * @param msg
     * @return
     */
    public static ApiResponse fail(Integer code, String msg) {
        ApiResponse response = new ApiResponse();
        response.setCode(code);
        response.setMsg(msg);
        return response;
    }

    public static ApiResponse fail(ApiResponseEnum responseEnum) {
        return fail(responseEnum, "");
    }

    public static ApiResponse fail(ApiResponseEnum responseEnum, String msg) {
        ApiResponse response = new ApiResponse();
        response.setCode(responseEnum.getCode());
        response.setMsg(responseEnum.getDescription() + ":" + msg);
        return response;
    }

    public static <T> ApiResponse success(T data){
        ApiResponse response = new ApiResponse();
        response.setCode(ApiResponseEnum.SUCCESS.getCode());
        response.setMsg(ApiResponseEnum.SUCCESS.getDescription());
        response.setSubCode(0);
        response.setSubMsg("ok");
        response.setData(data);
        return response;
    }

    public ApiResponse(){}

    public ApiResponse(ApiResponseEnum responseEnum, T data) {
        this.code = responseEnum.getCode();
        this.msg = responseEnum.getDescription();
        this.subCode = 0;
        this.subMsg = "";
        this.data = data;
    }

    public ApiResponse(ApiResponseEnum responseEnum, Integer subCode, String subMsg, T data) {
        this.code = responseEnum.getCode();
        this.msg = responseEnum.getDescription();
        this.subCode = subCode;
        this.subMsg = subMsg;
        this.data = data;
    }

}