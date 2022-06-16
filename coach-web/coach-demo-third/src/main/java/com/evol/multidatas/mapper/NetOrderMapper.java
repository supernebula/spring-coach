package com.evol.multidatas.mapper;

import com.evol.multidatas.domain.model.NetOrder;
import com.evol.multidatas.domain.model.NetOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NetOrderMapper {
    long countByExample(NetOrderExample example);

    int deleteByExample(NetOrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(NetOrder record);

    int insertSelective(NetOrder record);

    List<NetOrder> selectByExample(NetOrderExample example);

    NetOrder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") NetOrder record, @Param("example") NetOrderExample example);

    int updateByExample(@Param("record") NetOrder record, @Param("example") NetOrderExample example);

    int updateByPrimaryKeySelective(NetOrder record);

    int updateByPrimaryKey(NetOrder record);
}