package com.evol.domain.request;


import lombok.Data;

import java.io.Serializable;

@Data
public class
UpdateUserBalanceParam implements Serializable {

    private static final long serialVersionUID = 1L;

    private int changeMoney;

    private int userId;

    private Integer MoneyInOutType;

    private String tradeNo;

}

