package com.evol.mapper;

import com.evol.domain.model.NetOrders;
import com.evol.domain.model.NetOrdersExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NetOrdersMapper {
    long countByExample(NetOrdersExample example);

    int deleteByExample(NetOrdersExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(NetOrders record);

    int insertSelective(NetOrders record);

    List<NetOrders> selectByExample(NetOrdersExample example);

    NetOrders selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") NetOrders record, @Param("example") NetOrdersExample example);

    int updateByExample(@Param("record") NetOrders record, @Param("example") NetOrdersExample example);

    int updateByPrimaryKeySelective(NetOrders record);

    int updateByPrimaryKey(NetOrders record);
}