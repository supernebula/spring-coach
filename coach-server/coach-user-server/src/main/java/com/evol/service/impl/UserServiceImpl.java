package com.evol.service.impl;

import com.evol.domain.UpdateUserBalanceResult;
import com.evol.domain.dto.UserAddDto;
import com.evol.domain.dto.UserBalanceDTO;
import com.evol.domain.dto.UserModifyDto;
import com.evol.domain.model.User;
import com.evol.domain.model.UserBalanceRecord;
import com.evol.domain.request.UpdateUserBalanceParam;
import com.evol.enums.Gender;
import com.evol.mapper.UserBalanceRecordMapper;
import com.evol.mapper.UserMapper;
import com.evol.mapper.custom.UserCustomMapper;
import com.evol.service.UserService;
import com.evol.util.MD5Util;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.UUID;

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
        userBalanceRecord.setMoneyInOutType(updateParam.getMoneyInOutType());
        userBalanceRecord.setUserTransRecordId(updateParam.getTradeId());
        userBalanceRecord.setRemark("");
        userBalanceRecord.setCreateTime(new Date());
        //更多赋值语句
        userBalanceRecordMapper.insert(userBalanceRecord);

        return UpdateUserBalanceResult.trueResult(updateParam.getTradeId(), "更新成功");
    }

    @Override
    public Integer AddUser(UserAddDto userAddDto) {
        if(userAddDto == null){return null;}
        User user = new User();
        user.setUsername(userAddDto.getUsername());
        user.setSalt(UUID.randomUUID().toString());
        String password = MD5Util.MD5(userAddDto.getPassword(), user.getSalt());
        user.setPassword(password);
        user.setBalance(0);
        user.setNickname(userAddDto.getNickname());
        user.setOpenId(StringUtils.EMPTY);
        user.setAccessToken(StringUtils.EMPTY);
        user.setTokenExpiresIn(new Date());
        user.setRefreshToken(StringUtils.EMPTY);
        user.setScope(StringUtils.EMPTY);
        user.setUserImg(StringUtils.EMPTY);
        user.setMobile(userAddDto.getMobile());
        user.setGender(Gender.NONE.getCode());
        user.setProvince(userAddDto.getProvince());
        user.setCity(userAddDto.getCity());
        user.setCounty(userAddDto.getCounty());
        user.setCreateTime(new Date());
        Integer id = userMapper.insert(user);
        return id;
    }

    @Override
    public Integer ModifyUser(UserModifyDto userModifyDto) {
        if(userModifyDto == null || userModifyDto.getUserId() == null){return null;}
        if(!userModifyDto.getPassword().equals(userModifyDto.getConfirmPassword())){
            throw new IllegalArgumentException("两次新密码不同，请重新输入");
        }

        User user = userMapper.selectByPrimaryKey(userModifyDto.getUserId());
        if(user == null) {return null;}
        user.setMobile(userModifyDto.getMobile());
        String password = MD5Util.MD5(userModifyDto.getPassword(), user.getSalt());
        user.setPassword(password);
        user.setNickname(user.getNickname());
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public User getUserById(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public Integer deleteUserById(Integer userId) {
        return userMapper.deleteByPrimaryKey(userId);
    }
}
