package com.evol.mapper.user;

import com.evol.domain.model.user.UserBalanceRecord;
import com.evol.domain.model.user.UserBalanceRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserBalanceRecordMapper {
    long countByExample(UserBalanceRecordExample example);

    int deleteByExample(UserBalanceRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserBalanceRecord record);

    int insertSelective(UserBalanceRecord record);

    List<UserBalanceRecord> selectByExample(UserBalanceRecordExample example);

    UserBalanceRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserBalanceRecord record, @Param("example") UserBalanceRecordExample example);

    int updateByExample(@Param("record") UserBalanceRecord record, @Param("example") UserBalanceRecordExample example);

    int updateByPrimaryKeySelective(UserBalanceRecord record);

    int updateByPrimaryKey(UserBalanceRecord record);
}