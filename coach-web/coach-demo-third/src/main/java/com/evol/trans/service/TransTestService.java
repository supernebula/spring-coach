package com.evol.trans.service;

import com.evol.trans.domain.User;
import com.evol.trans.exception.CustomBusinessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransTestService {

    /**
     * 简单事务
     */
    @Transactional
    public void insertUser2() {
        User user = new User();
        //向数据库插入一条记录
        //todo: userMapper.insertOneUser(user);
        //手动模拟抛出异常
        throw new RuntimeException("发生异常");
    }


    /**
     * 带自定义异常处理的事务
     * 超时时间30S，发生CustomBusinessException回滚
     */
    @Transactional(timeout = 30, noRollbackFor = CustomBusinessException.class)
    public void insertUser() {
        User user = new User();
        //向数据库插入一条记录
        //todo: userMapper.insertOneUser(user);
        //手动模拟抛出异常
        throw new CustomBusinessException(0, "发生异常");
    }
}
