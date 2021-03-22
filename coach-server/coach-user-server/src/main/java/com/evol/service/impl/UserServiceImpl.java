package com.evol.service.impl;

import com.evol.domain.UpdateUserBalanceResult;
import com.evol.domain.dto.UserBalanceDTO;
import com.evol.domain.model.User;
import com.evol.domain.model.UserBalanceRecord;
import com.evol.domain.request.UpdateUserBalanceParam;
import com.evol.mapper.UserBalanceRecordMapper;
import com.evol.mapper.UserMapper;
import com.evol.mapper.custom.UserCustomMapper;
import com.evol.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

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
        User user = userMapper.selectByPrimaryKey(updateParam.getUserId());
        if(user == null){
            return UpdateUserBalanceResult.falseResult(updateParam.getTradeId(), "找不到指定的用户记录");
        }
        Integer count = userCustomMapper.updateUserBalance(updateParam.getChangeMoney(), updateParam.getUserId());
        if(count < 0){
            return UpdateUserBalanceResult.falseResult(updateParam.getTradeId(), "更新用户约失败");
        }


        UserBalanceRecord userBalanceRecord = new UserBalanceRecord();
        userBalanceRecord.setUserId(updateParam.getUserId());
        userBalanceRecord.setHistoryBalanceMoney(user.getBalance());
        userBalanceRecord.setCurrentBalanceMoney(user.getBalance() + updateParam.getChangeMoney());
        userBalanceRecord.setChangeMoney(updateParam.getChangeMoney());
        userBalanceRecord.setMoneyInOutType(updateParam.getMoneyInOutType().getCode());
        userBalanceRecord.setUserTransRecordId(updateParam.getTradeId());
        userBalanceRecord.setRemark("");
        userBalanceRecord.setCreateTime(new Date());
        //更多赋值语句
        userBalanceRecordMapper.insert(userBalanceRecord);

        return UpdateUserBalanceResult.trueResult(updateParam.getTradeId(), "更新成功");
    }
}
