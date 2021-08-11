package com.evol.service.impl;

import com.evol.domain.PageBase;
import com.evol.domain.model.UserBalanceRecord;
import com.evol.domain.model.UserBalanceRecordExample;
import com.evol.mapper.UserBalanceRecordMapper;
import com.evol.service.UserBalanceRecordService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public PageBase<UserBalanceRecord> queryBalanceRecord(Integer userId, Integer pageNo, Integer pageSize) {
        Page page =  PageHelper.startPage(pageNo, pageSize);
        UserBalanceRecordExample example = new UserBalanceRecordExample();
        example.createCriteria();
        List<UserBalanceRecord> recordList = userBalanceRecordMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(recordList)){
            return PageBase.create(page.getTotal(), pageNo, pageSize, new ArrayList<>());
        }
        return PageBase.create(page.getTotal(), pageNo, pageSize, recordList);
    }
}
