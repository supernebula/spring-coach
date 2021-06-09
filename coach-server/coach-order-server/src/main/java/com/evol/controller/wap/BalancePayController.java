package com.evol.controller.wap;

import com.evol.domain.model.NetOrder;
import com.evol.domain.request.PayOrderParam;
import com.evol.domain.request.PayParam;
import com.evol.domain.response.PaidHandleOrderResult;
import com.evol.enums.ApiResponseEnum;
import com.evol.service.NetOrderService;
import com.evol.web.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/balancePay")
@RestController
public class BalancePayController {

    @Autowired
    private NetOrderService netOrderService;

    @GetMapping("/pay")
    public ApiResponse pay(@RequestParam PayParam payParam){
        NetOrder order = netOrderService.getNetOrderById(payParam.getOrderId());
        if(order == null){
            return ApiResponse.fail(ApiResponseEnum.USER_DEFINED_ERROR, "没有指定的订单记录");
        }
        PaidHandleOrderResult result = netOrderService.payByBalance(order);
        return ApiResponse.success(result);
    }

}
