package com.evol.service;

import com.evol.domain.PageBase;
import com.evol.domain.model.UserBalanceRecord;

public interface UserBalanceRecordService {

    Integer insertBalanceRecord(UserBalanceRecord userBalanceRecord);

    UserBalanceRecord getBalanceRecordById(Integer id);

    PageBase<UserBalanceRecord> queryBalanceRecord(Integer userId, Integer page, Integer pageSize);
}
