package com.evol.service.impl;

import com.evol.domain.PageBase;
import com.evol.domain.UpdateUserBalanceResult;
import com.evol.domain.dto.UserAddDto;
import com.evol.domain.dto.UserBalanceDTO;
import com.evol.domain.dto.UserModifyDto;
import com.evol.domain.model.User;
import com.evol.domain.model.UserBalanceRecord;
import com.evol.domain.model.UserExample;
import com.evol.domain.request.UpdateUserBalanceParam;
import com.evol.domain.request.UserQueryRequest;
import com.evol.enums.Gender;
import com.evol.enums.MoneyInOutTypeEnum;
import com.evol.mapper.UserBalanceRecordMapper;
import com.evol.mapper.UserMapper;
import com.evol.mapper.custom.UserCustomMapper;
import com.evol.service.UserService;
import com.evol.util.MD5Util;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserBalanceRecordMapper userBalanceRecordMapper;

    @Autowired
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
    public PageBase<User> queryPage(UserQueryRequest userQueryRequest) {
        Page<Object> page = PageHelper.startPage(userQueryRequest.getPage(), userQueryRequest.getPageSize(),"id asc");
        //Page page =  PageHelper.startPage(movieQueryRequest.getPageNo(), movieQueryRequest.getPageSize());

        UserExample userExample = new UserExample();
        if(!StringUtils.isBlank(userQueryRequest.getUsername())){
            userExample.createCriteria().andUserImgEqualTo(userQueryRequest.getUsername());
        }

        if(!StringUtils.isBlank(userQueryRequest.getNickname())){
            userExample.createCriteria().andNicknameEqualTo("%" + userQueryRequest.getNickname() + "%");
        }

        if(!StringUtils.isBlank(userQueryRequest.getMobile())){
            userExample.createCriteria().andMobileEqualTo(userQueryRequest.getMobile());
        }


        List<User> userList = userMapper.selectByExample(userExample);
        if(CollectionUtils.isEmpty(userList)){
            return PageBase.create(page.getTotal(), userQueryRequest.getPage(), userQueryRequest.getPageSize(),
                    new ArrayList<>());
        }
        return PageBase.create(page.getTotal(), userQueryRequest.getPage(), userQueryRequest.getPageSize(),userList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public synchronized UpdateUserBalanceResult updateUserBalance(UpdateUserBalanceParam updateParam) {
        User user = userMapper.selectByPrimaryKey(updateParam.getUserId());
        if(user == null){
            return UpdateUserBalanceResult.falseResult(updateParam.getTradeNo(), "找不到指定的用户记录");
        }

        if(MoneyInOutTypeEnum.RECHARGE.getCode().equals(updateParam.getMoneyInOutType())
        || MoneyInOutTypeEnum.REFUND.getCode().equals(updateParam.getMoneyInOutType())){
            updateParam.setChangeMoney(Math.abs(updateParam.getChangeMoney()));
        }
        if(MoneyInOutTypeEnum.CONSUME.getCode().equals(updateParam.getMoneyInOutType())){
            updateParam.setChangeMoney(-Math.abs(updateParam.getChangeMoney()));
        }

        Integer count = userCustomMapper.updateUserBalance(updateParam.getChangeMoney(), updateParam.getUserId());
        if(count < 0){
            return UpdateUserBalanceResult.falseResult(updateParam.getTradeNo(), "更新用户约失败");
        }


        UserBalanceRecord userBalanceRecord = new UserBalanceRecord();
        userBalanceRecord.setUserId(updateParam.getUserId());
        userBalanceRecord.setHistoryBalanceMoney(user.getBalance());
        userBalanceRecord.setCurrentBalanceMoney(user.getBalance() + updateParam.getChangeMoney());
        userBalanceRecord.setChangeMoney(updateParam.getChangeMoney());
        userBalanceRecord.setMoneyInOutType(updateParam.getMoneyInOutType());
        userBalanceRecord.setUserTradeNo(updateParam.getTradeNo());
        userBalanceRecord.setRemark("");
        userBalanceRecord.setCreateTime(new Date());
        //更多赋值语句
        userBalanceRecordMapper.insert(userBalanceRecord);

        return UpdateUserBalanceResult.trueResult(updateParam.getTradeNo(), "更新成功");
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

    @Override
    public User saveWechatToken(String openId, String token, Date tokenExpires, String refreshToken, String scope) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andOpenIdEqualTo(openId);
        List<User> userList = userMapper.selectByExample(userExample);
        User user = userList.size() > 0  ? userList.get(0) : null;

        if(user == null){
            user = new User();
            user.setUsername(openId);
            user.setPassword("");
            user.setSalt("");
            user.setBalance(0);
            user.setNickname("");
            user.setOpenId(openId);
            user.setAccessToken(token);
            user.setTokenExpiresIn(tokenExpires);
            user.setRefreshToken(refreshToken);
            user.setScope(scope);
            user.setUserImg("");
            user.setMobile("");
            user.setGender(Gender.NONE.getCode());
            user.setProvince("");
            user.setCity("");
            user.setCounty("");
            user.setLastLoginTime(null);
            user.setCreateTime(new Date());
            user.setUpdateTime(null);
            Integer id = userMapper.insert(user);
            return user;
        }

        user.setAccessToken(token);
        user.setTokenExpiresIn(tokenExpires);
        user.setRefreshToken(refreshToken);
        user.setScope(scope);
        userMapper.updateByPrimaryKey(user);
        return user;
    }
}
