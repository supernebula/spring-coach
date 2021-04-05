package com.evol.service;

import com.evol.domain.model.order.NetOrders;

import java.util.List;

public interface NetOrdersService {
    List<NetOrders> getAllOrderByUserId(Integer userId);
}
