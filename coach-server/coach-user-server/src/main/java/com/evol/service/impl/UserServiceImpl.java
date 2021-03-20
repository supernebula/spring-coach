package com.evol.service.impl;

import com.evol.domain.UpdateUserBalanceResult;
import com.evol.domain.dto.UserBalanceDTO;
import com.evol.domain.model.User;
import com.evol.domain.model.UserBalanceRecord;
import com.evol.domain.model.UserExample;
import com.evol.domain.request.UpdateUserBalanceParam;
import com.evol.mapper.UserBalanceRecordMapper;
import com.evol.mapper.UserMapper;
import com.evol.mapper.custom.UserCustomMapper;
import com.evol.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserBalanceRecordMapper userBalanceRecordMapper;

    private UserCustomMapper userCustomMapper;

    @Override
    public UserBalanceDTO queryBalance(Integer userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        UserBalanceDTO dto = new UserBalanceDTO();
        if(user == null) { return dto; }
        dto.setUserId(user.getId());
        dto.setBalance(user.getBalance());
        dto.setUsername(user.getUsername());
        return dto;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public synchronized UpdateUserBalanceResult updateUserBalance(UpdateUserBalanceParam updateParam) {
        Integer count = userCustomMapper.updateUserBalance(updateParam.getChangeMoney(), updateParam.getUserId());
        UpdateUserBalanceResult result = new UpdateUserBalanceResult();
        if(count < 0){
            result.setSuccess(false);
            result.setTradeId(updateParam.getTradeId());
        }

        UserBalanceRecord userBalanceRecord = new UserBalanceRecord();
        userBalanceRecord.setUserId(updateParam.getUserId());
        //更多赋值语句

        userBalanceRecordMapper.insert(userBalanceRecord);

        result.setSuccess(true);
        result.setTradeId(updateParam.getTradeId());
        return result;
    }
}
