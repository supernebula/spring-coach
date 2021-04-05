package com.evol.mapper.order;

import com.evol.domain.model.order.NetOrders;
import com.evol.domain.model.order.NetOrdersExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NetOrdersMapper {
    long countByExample(NetOrdersExample example);

    int deleteByExample(NetOrdersExample example);

    int insert(NetOrders record);

    int insertSelective(NetOrders record);

    List<NetOrders> selectByExample(NetOrdersExample example);

    int updateByExampleSelective(@Param("record") NetOrders record, @Param("example") NetOrdersExample example);

    int updateByExample(@Param("record") NetOrders record, @Param("example") NetOrdersExample example);
}