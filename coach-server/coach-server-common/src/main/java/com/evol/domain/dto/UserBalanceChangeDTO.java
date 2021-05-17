package com.evol.domain.dto;

import com.evol.enums.MoneyInOutTypeEnum;
import lombok.Data;

@Data
public class UserBalanceChangeDTO {

    public Integer userId;

    /**
     * 金额（单位：分）
     */
    public Integer money;

    public MoneyInOutTypeEnum changeType;

}
