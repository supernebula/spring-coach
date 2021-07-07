package com.evol.service;

import com.evol.domain.PageBase;
import com.evol.domain.model.NetOrder;
import com.evol.domain.request.OrderCancelParam;
import com.evol.domain.request.PayOrderParam;
import com.evol.domain.response.CreateOrderResult;
import com.evol.domain.response.PaidHandleOrderResult;
import com.evol.web.ApiResponse;

import java.util.Date;

public interface NetOrderService {

//    CreateOrderResult newOrder(CreateOrderParam reqParam);

    CreateOrderResult newOrder(Integer movieId, Integer userId);

    PaidHandleOrderResult paidHandleOrder(PayOrderParam payOrderParam);

    NetOrder getByOrderNo(String orderNo);

    NetOrder getNetOrderById(Integer id);

    PageBase<NetOrder> queryNetOrder(Integer userId, Integer page, Integer pageSize);

    ApiResponse<PaidHandleOrderResult> payByBalance(Integer userId, Integer orderId);

    boolean expireCanceledOrder(OrderCancelParam param);

    PaidHandleOrderResult payByBalance(NetOrder netOrder);

    void cancelDelayNotPaidOrder(Integer orderId, String orderNo, Date createTime);

}
