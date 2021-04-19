package com.evol.service;

import com.evol.domain.PageBase;
import com.evol.domain.model.NetOrders;
import com.evol.domain.request.CreateOrderParam;
import com.evol.domain.request.PayOrderParam;
import com.evol.domain.response.CreateOrderResult;
import com.evol.domain.response.PaidHandleOrderResult;

public interface NetOrderService {

    CreateOrderResult newOrder(CreateOrderParam reqParam);

    PaidHandleOrderResult paidHandleOrder(PayOrderParam payOrderParam);

    NetOrders getByOrderNo(String orderNo);

    NetOrders getNetOrderById(Integer id);

    PageBase<NetOrders> queryNetOrder(Integer page, Integer pageSize);

}
