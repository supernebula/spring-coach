package com.evol.controller;

import com.evol.service.NetOrderService;
import org.springframework.beans.factory.annotation.Autowired;

public class WxCallbackController {

    @Autowired
    private NetOrderService netOrderService;

    public void callback(){
        netOrderService.paidOrder();
    }

}
