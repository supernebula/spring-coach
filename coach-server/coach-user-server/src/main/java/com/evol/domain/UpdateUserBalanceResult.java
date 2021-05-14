package com.evol.domain;

import lombok.Data;

@Data
public class UpdateUserBalanceResult {

    private boolean success;

    private String tradeNo;

    private String message;

    public static UpdateUserBalanceResult falseResult(String tradeNo, String message){
        UpdateUserBalanceResult result = new UpdateUserBalanceResult();
        result.setSuccess(false);
        result.setTradeNo(tradeNo);
        result.setMessage(message);
        return result;
    }

    public static UpdateUserBalanceResult trueResult(String tradeNo, String message){
        UpdateUserBalanceResult result = new UpdateUserBalanceResult();
        result.setSuccess(true);
        result.setTradeNo(tradeNo);
        result.setMessage(message);
        return result;
    }

}
