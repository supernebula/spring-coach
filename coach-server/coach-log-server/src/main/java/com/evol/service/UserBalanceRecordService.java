package com.evol.service;

import com.evol.domain.model.user.UserBalanceRecord;

import java.util.List;

public interface UserBalanceRecordService {

    List<UserBalanceRecord> getAllRecordByUserId(Integer userId);

}
