package com.evol.domain.response;

import lombok.Data;

@Data
public class CreateOrderResult {

    private Integer userId;

    private Integer orderId;

    private String orderNo;

    private Integer amount;

    private String movieName;

    private boolean success;

    private String message;

    public CreateOrderResult(boolean success, Integer userId, Integer orderId, String orderNo, Integer amount,
                             String  movieName,
                             String message){
        this.success = success;
        this.userId = userId;
        this.orderId = orderId;
        this.orderNo = orderNo;
        this.amount = amount;
        this.movieName = movieName;
        this.message = message;
    }

}
