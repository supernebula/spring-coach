package com.evol.domain.response;

import lombok.Data;

@Data
public class PaidHandleOrderResult {

    private Integer orderId;

    private boolean success;

    private String message;

    public static PaidHandleOrderResult noOrderRecord(String orderNo){
        PaidHandleOrderResult result = new PaidHandleOrderResult();
        result.setSuccess(false);
        result.setMessage("找不到指定的订单，orderNo：" + orderNo);
        return result;
    }

    public static PaidHandleOrderResult success(Integer orderId, String orderNo){
        PaidHandleOrderResult result = new PaidHandleOrderResult();
        result.setOrderId(orderId);
        result.setSuccess(true);
        result.setMessage("更新订单成功，orderNo：" + orderNo);
        return result;
    }
}
