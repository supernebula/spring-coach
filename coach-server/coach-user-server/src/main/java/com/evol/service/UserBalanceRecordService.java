package com.evol.service;

import com.evol.domain.model.UserBalanceRecord;

public interface UserBalanceRecordService {

    Integer insertBalanceRecord(UserBalanceRecord userBalanceRecord);

    UserBalanceRecord getBalanceRecordById(Integer id);
}
