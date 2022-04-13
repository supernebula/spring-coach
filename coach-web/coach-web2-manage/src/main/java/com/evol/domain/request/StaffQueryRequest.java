package com.evol.domain.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class StaffQueryRequest {
    @ApiModelProperty(name = "loginName", value = "登录名")
    private String loginName;

    @ApiModelProperty(name = "realName", value = "真实姓名")
    private String realName;

    @ApiModelProperty(name = "mobile", value = "手机号")
    private String mobile;

    //@NotNull(message = "页码不能为空")
    @ApiModelProperty(name = "page", value = "页码", required = true)
    private Integer page = 1;

    //@NotNull(message = "页码容量不能为空")
    @ApiModelProperty(name = "pageSize", value = "页面条数", required = true)
    private Integer pageSize = 30;
}
