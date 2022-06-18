package com.evol.swagg.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;

@ApiModel(description = "用户对象user")
@Data
public class UserDTO {
    @ApiModelProperty(value="用户id")
    private int id;
    @ApiModelProperty(value="用户名",name="username")
    private String username;
    @ApiModelProperty(value="登录时间")
    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss")
    private Date loginTime;
}
