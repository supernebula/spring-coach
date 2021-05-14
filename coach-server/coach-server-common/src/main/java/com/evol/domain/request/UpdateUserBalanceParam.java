package com.evol.domain.request;


import lombok.Data;

@Data
public class UpdateUserBalanceParam {

    private int changeMoney;

    private int userId;

    private Integer MoneyInOutType;

    private String tradeNo;

}

