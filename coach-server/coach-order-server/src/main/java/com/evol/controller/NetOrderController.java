package com.evol.controller;

import com.evol.domain.PageBase;
import com.evol.domain.model.NetOrders;
import com.evol.domain.request.CreateOrderParam;
import com.evol.domain.response.CreateOrderResult;
import com.evol.service.NetOrderService;
import com.evol.web.ApiResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/netOrder")
public class NetOrderController {

    @Autowired
    private NetOrderService netOrderService;

    public ApiResponse newOrder(CreateOrderParam reqParam){
        CreateOrderResult resp = netOrderService.newOrder(reqParam);
        return new ApiResponse(resp);
    }

    @ApiOperation(value = "分页查询", response = ApiResponse.class)
    @GetMapping("/queryRecord")
    public ApiResponse queryRecord(Integer userId, Integer pageNo, Integer pageSize){
        //分页查询
        PageBase<NetOrders> pageResult = netOrderService.queryNetOrder(pageNo, pageSize);
        return ApiResponse.success(pageResult);
    }

    @ApiOperation(value = "根据ID获取网络订单", response = ApiResponse.class)
    @GetMapping("getRecord")
    public ApiResponse getRecord(Integer id){
        NetOrders item = netOrderService.getNetOrderById(id);
        return ApiResponse.success(item);
    }
}
