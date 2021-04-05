package com.evol.service.impl;

import com.evol.domain.model.order.NetOrders;
import com.evol.domain.model.order.NetOrdersExample;
import com.evol.mapper.order.NetOrdersMapper;
import com.evol.service.NetOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NetOrdersServiceImpl implements NetOrdersService {

    @Autowired
    private NetOrdersMapper netOrdersMapper;

    @Override
    public List<NetOrders> getAllOrderByUserId(Integer userId) {

        NetOrdersExample example = new NetOrdersExample();
        example.createCriteria().andUserIdEqualTo(userId);
        List<NetOrders> list = netOrdersMapper.selectByExample(example);
        return list;
    }
}
