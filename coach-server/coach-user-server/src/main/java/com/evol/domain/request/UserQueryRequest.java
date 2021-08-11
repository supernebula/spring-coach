package com.evol.domain.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author admin
 */
@Data
public class UserQueryRequest {

    @ApiModelProperty(name = "username", value = "用户名")
    private String username;

    @ApiModelProperty(name = "nickname", value = "昵称")
    private String nickname;

    @ApiModelProperty(name = "mobile", value = "手机号")
    private String mobile;

    //@NotNull(message = "页码不能为空")
    @ApiModelProperty(name = "page", value = "页码", required = true)
    private Integer page = 1;

    //@NotNull(message = "页码容量不能为空")
    @ApiModelProperty(name = "pageSize", value = "页面条数", required = true)
    private Integer pageSize = 30;
}
