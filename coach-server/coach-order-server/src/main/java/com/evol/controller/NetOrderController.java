package com.evol.controller;

import com.evol.domain.ApiResponse;
import com.evol.domain.request.CreateOrderRequest;
import com.evol.domain.response.CreateOrderResponse;
import com.evol.service.NetOrderService;
import org.springframework.beans.factory.annotation.Autowired;

public class NetOrderController {

    @Autowired
    private NetOrderService netOrderService;

    public ApiResponse newOrder(CreateOrderRequest reqParam){
        CreateOrderResponse resp = netOrderService.newOrder(reqParam);
        return new ApiResponse(resp);
    }
}
