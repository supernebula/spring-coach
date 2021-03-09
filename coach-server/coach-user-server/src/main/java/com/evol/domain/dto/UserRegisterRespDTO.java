package com.evol.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "用户返回参数")
public class UserRegisterRespDTO {

    @ApiModelProperty(value = "token", required = true)
    private String token;

    @ApiModelProperty(value = "user_id", required = true)
    private String userId;

    @ApiModelProperty(value = "姓名", required = true)
    private String userName;

    @ApiModelProperty(value = "昵称", required = true)
    private String nickName;

    @ApiModelProperty(value = "openid", required = true)
    private String openid;

    @ApiModelProperty(value = "头像", required = true)
    private String userImg;

    @ApiModelProperty(value = "手机号", required = true)
    private String mobile;

    @ApiModelProperty(value = "1、男，2、女，3、未知", required = true)
    private String sex;

    @ApiModelProperty(value = "省", required = true)
    private String province;

    @ApiModelProperty(value = "市", required = true)
    private String city;

    @ApiModelProperty(value = "县", required = true)
    private String county;

    @ApiModelProperty(value = "创建时间", required = true)
    private String createTime;

    @ApiModelProperty(value = "更新时间", required = true)
    private String updateTime;
}
