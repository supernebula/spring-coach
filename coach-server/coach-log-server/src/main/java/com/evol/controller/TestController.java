package com.evol.controller;

import com.evol.domain.model.business.Movie;
import com.evol.domain.model.order.NetOrders;
import com.evol.domain.model.user.UserBalanceRecord;
import com.evol.service.business.MovieService;
import com.evol.service.order.NetOrdersService;
import com.evol.service.user.UserBalanceRecordService;
import com.evol.web.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/test")
@RestController
public class TestController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private NetOrdersService netOrdersService;

    @Autowired
    private UserBalanceRecordService userBalanceRecordService;

    @PostMapping("multi")
    public ApiResponse multiTest(){
        Map<String, Object> map = new HashMap<>();
        Movie movie = movieService.getMovieById(1);
        List<NetOrders> orderList = netOrdersService.getAllOrderByUserId(1);
        List<UserBalanceRecord> balanceRecordList = userBalanceRecordService.getAllRecordByUserId(1);
        map.put("movie", movie);
        map.put("orderList", orderList);
        map.put("balanceRecordList", balanceRecordList);
        ApiResponse<Map> resp = ApiResponse.success(map);
        return resp;
    }
}
