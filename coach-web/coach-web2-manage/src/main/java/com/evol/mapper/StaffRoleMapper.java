package com.evol.mapper;

import com.evol.domain.model.StaffRole;
import com.evol.domain.model.StaffRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StaffRoleMapper {
    long countByExample(StaffRoleExample example);

    int deleteByExample(StaffRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(StaffRole record);

    int insertSelective(StaffRole record);

    List<StaffRole> selectByExample(StaffRoleExample example);

    StaffRole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") StaffRole record, @Param("example") StaffRoleExample example);

    int updateByExample(@Param("record") StaffRole record, @Param("example") StaffRoleExample example);

    int updateByPrimaryKeySelective(StaffRole record);

    int updateByPrimaryKey(StaffRole record);
}