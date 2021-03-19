package com.evol.service;

import com.evol.domain.request.CreateOrderRequest;
import com.evol.domain.response.CreateOrderResponse;

public interface NetOrderService {

    CreateOrderResponse newOrder(CreateOrderRequest reqParam);

    void paidUpdateOrder();

}
