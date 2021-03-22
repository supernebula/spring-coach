package com.evol.service;

import com.evol.domain.UpdateUserBalanceResult;
import com.evol.domain.dto.UserBalanceDTO;
import com.evol.domain.request.UpdateUserBalanceParam;

public interface UserService {
    UserBalanceDTO queryBalance(Integer userId);

    UpdateUserBalanceResult updateUserBalance(UpdateUserBalanceParam updateParam);
}
