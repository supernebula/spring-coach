package com.evol.domain.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel
public class MovieQueryRequest {
    @ApiModelProperty(name = "name", value = "名称")
    private String name;

    //@NotNull(message = "页码不能为空")
    @ApiModelProperty(name = "page", value = "页码", required = true)
    private Integer page = 1;

    //@NotNull(message = "页码容量不能为空")
    @ApiModelProperty(name = "pageSize", value = "页面条数", required = true)
    private Integer pageSize = 30;
}
