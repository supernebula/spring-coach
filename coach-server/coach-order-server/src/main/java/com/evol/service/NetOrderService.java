package com.evol.service;

import com.evol.domain.PageBase;
import com.evol.domain.model.NetOrder;
import com.evol.domain.request.CreateOrderParam;
import com.evol.domain.request.PayOrderParam;
import com.evol.domain.response.CreateOrderResult;
import com.evol.domain.response.PaidHandleOrderResult;

public interface NetOrderService {

    CreateOrderResult newOrder(CreateOrderParam reqParam);

    PaidHandleOrderResult paidHandleOrder(PayOrderParam payOrderParam);

    NetOrder getByOrderNo(String orderNo);

    NetOrder getNetOrderById(Integer id);

    PageBase<NetOrder> queryNetOrder(Integer page, Integer pageSize);

}
