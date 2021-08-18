package com.evol.domain.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class NetOrderQueryRequest {

    @ApiModelProperty(name = "username", value = "用户名")
    private String username;

    @ApiModelProperty(name = "orderNo", value = "订单号")
    private String orderNo;

    @ApiModelProperty(name = "payOrderNo", value = "支付订单号")
    private String payOrderNo;


    //@NotNull(message = "页码不能为空")
    @ApiModelProperty(name = "page", value = "页码", required = true)
    private Integer page = 1;

    //@NotNull(message = "页码容量不能为空")
    @ApiModelProperty(name = "pageSize", value = "页面条数", required = true)
    private Integer pageSize = 30;

}
