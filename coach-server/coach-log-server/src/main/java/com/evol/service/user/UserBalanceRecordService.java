package com.evol.service.user;

import com.evol.domain.model.user.UserBalanceRecord;

import java.util.List;

public interface UserBalanceRecordService {

    List<UserBalanceRecord> getAllRecordByUserId(Integer userId);

}
