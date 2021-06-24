package com.evol.controller;

import com.evol.domain.PageBase;
import com.evol.domain.model.NetOrder;
import com.evol.domain.response.CreateOrderResult;
import com.evol.enums.ApiResponseEnum;
import com.evol.service.NetOrderService;
import com.evol.service.invoke.FeignMovieClient;
import com.evol.service.invoke.FeignUserClient;
import com.evol.web.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("用户端订单API")
@RestController
@RequestMapping("/client/netOrder")
public class NetOrderClientController {

    @Autowired
    NetOrderService netOrderService;

    @Autowired
    FeignMovieClient feignMovieClient;

    @Autowired
    FeignUserClient feignUserClient;

    @ApiOperation(value = "查询我的订单", response = ApiResponse.class)
    @GetMapping("/query")
    public ApiResponse query(Integer userId, Integer pageNo, Integer pageSize){
        //分页查询
        PageBase<NetOrder> pageResult = netOrderService.queryNetOrder(userId, pageNo, pageSize);
        return ApiResponse.success(pageResult);
    }

    @ApiOperation(value = "根据订单获取网络订单", response = ApiResponse.class)
    @GetMapping("get")
    public ApiResponse get(Integer orderId){
        NetOrder item = netOrderService.getNetOrderById(orderId);
        return ApiResponse.success(item);
    }

    @ApiOperation(value = "下单", response = ApiResponse.class)
    @GetMapping("buy")
    public ApiResponse buy(Integer movieId, Integer userId){

//        ApiResponse<MovieDetailDTO> apiResp = feignMovieClient.getMovie(movieId);
//        if(apiResp.getSubCode() != 0 || apiResp.getData() == null  ){
//            return ApiResponse.fail(ApiResponseEnum.NO_RECORD, "找不到指定的电影记录");
//        }
//
//        ApiResponse<UserDTO> userApiResp = feignUserClient.getUserById(userId);
//        if(userApiResp.getSubCode() != 0 || userApiResp.getData() == null  ){
//            ApiResponse.fail(ApiResponseEnum.NO_RECORD, "找不到指定的用户记录");
//        }
//
//        MovieDetailDTO movieDTO = apiResp.getData();
//        UserDTO userDTO = userApiResp.getData();
//        CreateOrderParam param = new CreateOrderParam();
//        param.setUserId(userId);
//        param.setUsername(userDTO.getUsername());
//        param.setMovieId(movieId);
//        param.setMovieName(movieDTO.getName());
//        param.setAmount(movieDTO.getDiscountPrice());
//        CreateOrderResult result = netOrderService.newOrder(param);
        CreateOrderResult result = netOrderService.newOrder(movieId, userId);
        return result.isSuccess() ? ApiResponse.success(result) :
                ApiResponse.fail(ApiResponseEnum.USER_DEFINED_ERROR , result.getMessage());
    }

    

}
