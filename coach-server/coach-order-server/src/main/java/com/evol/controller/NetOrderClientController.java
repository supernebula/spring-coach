package com.evol.controller;

import com.evol.domain.PageBase;
import com.evol.domain.model.NetOrder;
import com.evol.domain.request.NetOrderQueryRequest;
import com.evol.domain.response.CreateOrderResult;
import com.evol.enums.ApiResponseEnum;
import com.evol.service.NetOrderService;
import com.evol.service.invoke.FeignMovieClient;
import com.evol.service.invoke.FeignUserClient;
import com.evol.web.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api("用户端订单API")
@RestController
@RequestMapping("/client/netOrder")
//@CrossOrigin
public class NetOrderClientController {

    @Autowired
    NetOrderService netOrderService;

    @Autowired
    FeignMovieClient feignMovieClient;

    @Autowired
    FeignUserClient feignUserClient;

    //@CrossOrigin(value = "http://localhost:8090")
    @ApiOperation(value = "查询我的订单", response = ApiResponse.class)
    @GetMapping("/query")
    public ApiResponse query(NetOrderQueryRequest netOrderQueryRequest){
        //分页查询
        PageBase<NetOrder> pageResult = netOrderService.queryNetOrder(netOrderQueryRequest);
        return ApiResponse.success(pageResult);
    }

    //@CrossOrigin(value = "http://localhost:8090")
    @ApiOperation(value = "根据订单获取网络订单", response = ApiResponse.class)
    @GetMapping("get")
    public ApiResponse get(Integer orderId){
        NetOrder item = netOrderService.getNetOrderById(orderId);
        return ApiResponse.success(item);
    }

    //@CrossOrigin(value = "http://localhost:8090")
    @ApiOperation(value = "下单", response = ApiResponse.class)
    @PostMapping("buy")
    public ApiResponse buy(@RequestParam Integer movieId, @RequestParam Integer userId){

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
