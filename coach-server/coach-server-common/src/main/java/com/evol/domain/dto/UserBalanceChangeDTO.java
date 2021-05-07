package com.evol.domain.dto;

import com.evol.domain.UserBalanceChangeType;
import lombok.Data;

@Data
public class UserBalanceChangeDTO {

    public Integer userId;

    /**
     * 金额（单位：分）
     */
    public Integer money;

    public UserBalanceChangeType changeType;

}
