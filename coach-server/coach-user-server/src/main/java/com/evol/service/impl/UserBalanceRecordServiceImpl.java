package com.evol.service.impl;

import com.evol.domain.model.UserBalanceRecord;
import com.evol.mapper.UserBalanceRecordMapper;
import com.evol.service.UserBalanceRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserBalanceRecordServiceImpl implements UserBalanceRecordService {

    @Autowired
    private UserBalanceRecordMapper userBalanceRecordMapper;

    @Override
    public Integer insertBalanceRecord(UserBalanceRecord userBalanceRecord) {
        return userBalanceRecordMapper.insert(userBalanceRecord);
    }

    @Override
    public UserBalanceRecord getBalanceRecordById(Integer id) {
        return userBalanceRecordMapper.selectByPrimaryKey(id);
    }
}
