package com.evol.controller;

import com.evol.domain.PageBase;
import com.evol.domain.model.NetOrder;
import com.evol.domain.request.NetOrderQueryRequest;
import com.evol.service.NetOrderService;
import com.evol.web.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("订单管理")
@RestController
@RequestMapping("/web/netOrder")
@CrossOrigin
public class NetOrderWebController {

    @Autowired
    private NetOrderService netOrderService;

//    public ApiResponse newOrder(CreateOrderParam reqParam){
//        CreateOrderResult resp = netOrderService.newOrder(reqParam);
//        return new ApiResponse(resp);
//    }

    @ApiOperation(value = "分页查询", response = ApiResponse.class)
    @GetMapping("/query")
    @CrossOrigin(value = "http://localhost:8080")
    public ApiResponse query(NetOrderQueryRequest netOrderQueryRequest){
        //分页查询
        PageBase<NetOrder> pageResult = netOrderService.queryNetOrder(netOrderQueryRequest);
        return ApiResponse.success(pageResult);
    }

    @ApiOperation(value = "根据ID获取网络订单", response = ApiResponse.class)
    @GetMapping("get")
    @CrossOrigin(value = "http://localhost:8080")
    public ApiResponse get(Integer orderId){
        NetOrder item = netOrderService.getNetOrderById(orderId);
        return ApiResponse.success(item);
    }

    @ApiOperation(value = "退款", response = ApiResponse.class)
    @GetMapping("refund")
    @CrossOrigin(value = "http://localhost:8080")
    public ApiResponse refund(Integer orderId){
        NetOrder netOrder = netOrderService.getNetOrderById(orderId);
        return ApiResponse.success(netOrder);
    }
}
