package com.evol.service.order;

import com.evol.domain.model.order.NetOrders;

import java.util.List;

public interface NetOrdersService {
    List<NetOrders> getAllOrderByUserId(Integer userId);
}
