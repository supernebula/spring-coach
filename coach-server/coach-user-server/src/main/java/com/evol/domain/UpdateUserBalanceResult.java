package com.evol.domain;

import lombok.Data;

@Data
public class UpdateUserBalanceResult {

    private boolean success;

    private Integer tradeId;

    private String message;

    public static UpdateUserBalanceResult falseResult(Integer tradeId, String message){
        UpdateUserBalanceResult result = new UpdateUserBalanceResult();
        result.setSuccess(false);
        result.setTradeId(tradeId);
        result.setMessage(message);
        return result;
    }

    public static UpdateUserBalanceResult trueResult(Integer tradeId, String message){
        UpdateUserBalanceResult result = new UpdateUserBalanceResult();
        result.setSuccess(true);
        result.setTradeId(tradeId);
        result.setMessage(message);
        return result;
    }

}
