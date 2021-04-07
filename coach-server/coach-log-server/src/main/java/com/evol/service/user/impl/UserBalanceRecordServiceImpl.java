package com.evol.service.user.impl;

import com.evol.domain.model.user.UserBalanceRecord;
import com.evol.domain.model.user.UserBalanceRecordExample;
import com.evol.mapper.user.UserBalanceRecordMapper;
import com.evol.service.user.UserBalanceRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserBalanceRecordServiceImpl implements UserBalanceRecordService {

    @Autowired
    private UserBalanceRecordMapper userBalanceRecordMapper;

    @Override
    public List<UserBalanceRecord> getAllRecordByUserId(Integer userId) {
        UserBalanceRecordExample example = new UserBalanceRecordExample();
        example.createCriteria().andUserIdEqualTo(userId);
        List<UserBalanceRecord> list =
                userBalanceRecordMapper.selectByExample(example);
        return list;
    }
}
