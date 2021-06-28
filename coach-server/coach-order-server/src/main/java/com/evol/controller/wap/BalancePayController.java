package com.evol.controller.wap;

import com.evol.domain.model.NetOrder;
import com.evol.domain.request.PayOrderParam;
import com.evol.domain.request.PayParam;
import com.evol.domain.response.PaidHandleOrderResult;
import com.evol.enums.ApiResponseEnum;
import com.evol.service.NetOrderService;
import com.evol.web.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/pay")
@RestController
@CrossOrigin
public class BalancePayController {

    @Autowired
    private NetOrderService netOrderService;

    @CrossOrigin(value = "http://localhost:8090")
    @PostMapping(value = "/balance", produces = "application/json;charset=UTF-8")
    public ApiResponse pay(PayParam payParam){
        ApiResponse<PaidHandleOrderResult> apiResult = netOrderService.payByBalance(payParam.getUserId(),
                payParam.getOrderId());
        return apiResult;
    }

}
