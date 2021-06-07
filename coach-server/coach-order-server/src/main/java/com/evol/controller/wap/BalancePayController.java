package com.evol.controller.wap;

import com.evol.domain.request.PayOrderParam;
import com.evol.domain.response.PaidHandleOrderResult;
import com.evol.service.NetOrderService;
import com.evol.web.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/balancePay")
@RestController
public class BalancePayController {

    @Autowired
    private NetOrderService netOrderService;

    @GetMapping("/pay")
    public ApiResponse pay(Integer amount, Integer userId, Integer orderId){
        PayOrderParam payOrderParam = new PayOrderParam();
        PaidHandleOrderResult result = netOrderService.payByBalance(amount, userId, orderId);
        return ApiResponse.success(result);
    }

}
