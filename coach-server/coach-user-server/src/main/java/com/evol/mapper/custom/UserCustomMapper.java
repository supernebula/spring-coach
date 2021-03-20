package com.evol.mapper.custom;

import org.apache.ibatis.annotations.Param;

public interface UserCustomMapper {

    int updateUserBalance(@Param("changeMoney") Integer changeMoney,
                          @Param("userId") Integer userId);

}
