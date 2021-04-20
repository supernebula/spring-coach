package com.evol.controller;

import com.evol.domain.PageBase;
import com.evol.domain.model.NetOrder;
import com.evol.service.NetOrderService;
import com.evol.web.ApiResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client/netOrder")
public class NetOrderClientController {

    @Autowired
    NetOrderService netOrderService;

    @ApiOperation(value = "查询我的订单", response = ApiResponse.class)
    @GetMapping("/query")
    public ApiResponse query(Integer userId, Integer pageNo, Integer pageSize){
        //分页查询
        PageBase<NetOrder> pageResult = netOrderService.queryNetOrder(pageNo, pageSize);
        return ApiResponse.success(pageResult);
    }

    @ApiOperation(value = "根据ID获取网络订单", response = ApiResponse.class)
    @GetMapping("get")
    public ApiResponse get(Integer orderId){
        NetOrder item = netOrderService.getNetOrderById(orderId);
        return ApiResponse.success(item);
    }

}
