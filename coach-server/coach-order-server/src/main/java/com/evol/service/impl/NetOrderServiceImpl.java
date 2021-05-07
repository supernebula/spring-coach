package com.evol.service.impl;

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
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
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
        this.payByUserBalance(netOrder.getUserId(), netOrder.getAmount());
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
        return netOrdersMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageBase<NetOrder> queryNetOrder(Integer userId, Integer pageNo, Integer pageSize) {
        Page page =  PageHelper.startPage(pageNo, pageSize);
        NetOrderExample example = new NetOrderExample();
        example.createCriteria().andUserIdEqualTo(userId);
        List<NetOrder> movieList = netOrdersMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(movieList)){
            return PageBase.create(page.getTotal(), new ArrayList<>());
        }
        return PageBase.create(page.getTotal(),movieList);
    }



    public void payByUserBalance(Integer userId, Integer money){
        UpdateUserBalanceParam param = new UpdateUserBalanceParam();
        param.setUserId(userId);
        param.setChangeMoney(money);
        param.setMoneyInOutType(MoneyInOutTypeEnum.CONSUME.getCode());
        rabbitTemplate.convertAndSend(RabbitContants.USER_BALANCE_EXCHANGE, RabbitContants.MSG_ROUTING_KEY, "message1");

    }
}
