package com.evol.service.impl;

import com.alibaba.druid.support.json.JSONUtils;
import com.evol.domain.PageBase;
import com.evol.domain.model.NetOrder;
import com.evol.domain.model.NetOrderExample;
import com.evol.domain.request.CreateOrderParam;
import com.evol.domain.request.PayOrderParam;
import com.evol.domain.request.UpdateUserBalanceParam;
import com.evol.domain.response.CreateOrderResult;
import com.evol.domain.response.PaidHandleOrderResult;
import com.evol.enums.MoneyInOutTypeEnum;
import com.evol.mapper.NetOrderMapper;
import com.evol.service.NetOrderService;
import com.evol.util.JsonUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.rabbitmq.tools.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import com.evol.contant.RabbitContants;
import org.springframework.util.CollectionUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class NetOrderServiceImpl implements NetOrderService {

    @Autowired
    private NetOrderMapper netOrdersMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public CreateOrderResult newOrder(CreateOrderParam reqParam) {
        return null;
    }

    @Override
    public PaidHandleOrderResult paidHandleOrder(PayOrderParam payOrderParam) {
        NetOrder netOrder = this.getByOrderNo(payOrderParam.getOutTradeNo());
        if(netOrder == null){
            return PaidHandleOrderResult.noOrderRecord(payOrderParam.getOutTradeNo());
        }

        netOrder.setPayOrderNo(payOrderParam.getTransactionId());
        netOrder.setPayTime(payOrderParam.getTimeEnd());
        netOrdersMapper.updateByPrimaryKeySelective(netOrder);

        return PaidHandleOrderResult.success(payOrderParam.getOutTradeNo());
    }

    @Override
    public PaidHandleOrderResult payByBalance(Integer amount, Integer userId, Integer orderId) {
        NetOrder netOrder = this.getNetOrderById(orderId);
        if(netOrder == null){
            return PaidHandleOrderResult.noOrderRecord("" + orderId);
        }

        netOrder.setPayOrderNo("");
        netOrder.setPayTime(new Date());
        netOrdersMapper.updateByPrimaryKeySelective(netOrder);
        //余额支付
        this.updateUserBalance(netOrder.getUserId(), netOrder.getAmount(), netOrder.getOrderNo());
        return PaidHandleOrderResult.success(netOrder.getOrderNo());
    }



    @Override
    public NetOrder getByOrderNo(String orderNo) {
        NetOrderExample example = new NetOrderExample();
        example.createCriteria().andOrderNoEqualTo(orderNo);
        List<NetOrder> list = netOrdersMapper.selectByExample(example);
        return (list != null && list.size() > 0) ? list.get(0) : null;
    }

    @Override
    public NetOrder getNetOrderById(Integer id) {
        NetOrder order = new NetOrder();
        order.setId(1);
        order.setPayOrderNo("PY1111111");
        order.setPayTime(new Date());
        order.setAmount(100);
        order.setCreateTime(new Date());
        order.setMoviceId(1);
        order.setMoviceName("movice name1");
        order.setOrderNo("OR111111");
        order.setPaidAmount(100);
        order.setPayModeType("1");
        order.setStatus(1);
        order.setUserId(1);
        order.setUsername("user1");
        return order;
        //return netOrdersMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageBase<NetOrder> queryNetOrder(Integer userId, Integer pageNo, Integer pageSize) {
        Page<Object> page = PageHelper.startPage(pageNo, pageSize,"id asc");
        //PageHelper.startPage(pageNo, pageSize,"id asc");
        NetOrderExample example = new NetOrderExample();
        example.createCriteria().andUserIdEqualTo(userId);
        List<NetOrder> movieList = netOrdersMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(movieList)){
            return PageBase.create(page.getTotal(), new ArrayList<>());
        }
        return PageBase.create(page.getTotal(),movieList);
    }



    public void updateUserBalance(Integer userId, Integer money, String orderNo){
        UpdateUserBalanceParam param = new UpdateUserBalanceParam();
        param.setUserId(userId);
        param.setChangeMoney(money);
        param.setMoneyInOutType(MoneyInOutTypeEnum.CONSUME.getCode());
        param.setTradeNo(orderNo);
        String jsonStr = JsonUtil.ParseString(param);
        rabbitTemplate.convertAndSend(RabbitContants.USER_BALANCE_EXCHANGE, RabbitContants.USER_BALANCE_ROUTING_KEY, jsonStr);
    }
}
