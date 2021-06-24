package com.evol.domain.response;

import lombok.Data;

@Data
public class CreateOrderResult {

    private Integer orderId;

    private String orderNo;

    private boolean success;

    private String message;

    public CreateOrderResult(boolean success, Integer orderId, String orderNo, String message){
        this.success = success;
        this.orderId = orderId;
        this.orderNo = orderNo;
        this.message = message;
    }

}
