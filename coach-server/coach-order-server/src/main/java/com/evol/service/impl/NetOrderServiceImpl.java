package com.evol.service.impl;

import com.evol.domain.model.NetOrders;
import com.evol.domain.model.NetOrdersExample;
import com.evol.domain.request.CreateOrderParam;
import com.evol.domain.request.PayOrderParam;
import com.evol.domain.response.CreateOrderResult;
import com.evol.domain.response.PaidHandleOrderResult;
import com.evol.mapper.NetOrdersMapper;
import com.evol.service.NetOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import com.evol.contant.RabbitContants;


import java.util.List;

@Service
public class NetOrderServiceImpl implements NetOrderService {

    @Autowired
    private NetOrdersMapper netOrdersMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public CreateOrderResult newOrder(CreateOrderParam reqParam) {
        return null;
    }

    @Override
    public PaidHandleOrderResult paidHandleOrder(PayOrderParam payOrderParam) {
        NetOrders netOrder = this.selectByOrderNo(payOrderParam.getOutTradeNo());
        if(netOrder == null){
            return PaidHandleOrderResult.noOrderRecord(payOrderParam.getOutTradeNo());
        }

        netOrder.setPayOrderNo(payOrderParam.getTransactionId());
        netOrder.setPayTime(payOrderParam.getTimeEnd());
        netOrdersMapper.updateByPrimaryKeySelective(netOrder);
        return PaidHandleOrderResult.success(payOrderParam.getOutTradeNo());
    }

    @Override
    public NetOrders selectByOrderNo(String orderNo) {
        NetOrdersExample netOrdersExample = new NetOrdersExample();
        netOrdersExample.createCriteria().andOrderNoEqualTo(orderNo);
        List<NetOrders> orderList = netOrdersMapper.selectByExample(netOrdersExample);
        if(orderList.size() == 0){
            return null;
        }
        return orderList.get(0);
    }


    public void payByUserBalance(){

        rabbitTemplate.convertAndSend(RabbitContants.USER_BALANCE_EXCHANGE, RabbitContants.MSG_ROUTING_KEY, "message1");

    }
}
