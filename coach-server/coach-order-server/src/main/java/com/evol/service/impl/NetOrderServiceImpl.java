package com.evol.service.impl;

import com.evol.domain.PageBase;
import com.evol.domain.model.NetOrders;
import com.evol.domain.model.NetOrdersExample;
import com.evol.domain.request.CreateOrderParam;
import com.evol.domain.request.PayOrderParam;
import com.evol.domain.response.CreateOrderResult;
import com.evol.domain.response.PaidHandleOrderResult;
import com.evol.mapper.NetOrdersMapper;
import com.evol.service.NetOrderService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import com.evol.contant.RabbitContants;
import org.springframework.util.CollectionUtils;
import java.util.ArrayList;
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
        NetOrders netOrder = this.getByOrderNo(payOrderParam.getOutTradeNo());
        if(netOrder == null){
            return PaidHandleOrderResult.noOrderRecord(payOrderParam.getOutTradeNo());
        }

        netOrder.setPayOrderNo(payOrderParam.getTransactionId());
        netOrder.setPayTime(payOrderParam.getTimeEnd());
        netOrdersMapper.updateByPrimaryKeySelective(netOrder);
        return PaidHandleOrderResult.success(payOrderParam.getOutTradeNo());
    }

    @Override
    public NetOrders getByOrderNo(String orderNo) {
        NetOrdersExample example = new NetOrdersExample();
        example.createCriteria().andOrderNoEqualTo(orderNo);
        List<NetOrders> list = netOrdersMapper.selectByExample(example);
        return (list != null && list.size() > 0) ? list.get(0) : null;
    }

    @Override
    public NetOrders getNetOrderById(Integer id) {
        return netOrdersMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageBase<NetOrders> queryNetOrder(Integer pageNo, Integer pageSize) {
        Page page =  PageHelper.startPage(pageNo, pageSize);
        NetOrdersExample example = new NetOrdersExample();
        example.createCriteria();
        List<NetOrders> movieList = netOrdersMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(movieList)){
            return PageBase.create(page.getTotal(), new ArrayList<>());
        }
        return PageBase.create(page.getTotal(),movieList);
    }



    public void payByUserBalance(){

        rabbitTemplate.convertAndSend(RabbitContants.USER_BALANCE_EXCHANGE, RabbitContants.MSG_ROUTING_KEY, "message1");

    }
}
