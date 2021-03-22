package com.evol.controller;

import com.evol.domain.ApiResponse;
import com.evol.domain.request.CreateOrderParam;
import com.evol.domain.response.CreateOrderResult;
import com.evol.service.NetOrderService;
import org.springframework.beans.factory.annotation.Autowired;

public class NetOrderController {

    @Autowired
    private NetOrderService netOrderService;

    public ApiResponse newOrder(CreateOrderParam reqParam){
        CreateOrderResult resp = netOrderService.newOrder(reqParam);
        return new ApiResponse(resp);
    }
}
