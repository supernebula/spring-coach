package com.evol.domain.request;

import lombok.Data;

import java.util.Date;

@Data
public class OrderCancelParam {

    private Integer orderId;

    private String orderNo;

    private Date createTime;

}
