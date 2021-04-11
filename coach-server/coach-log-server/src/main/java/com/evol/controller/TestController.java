package com.evol.controller;

import com.evol.domain.model.business.Movie;
import com.evol.domain.model.order.NetOrders;
import com.evol.domain.model.user.UserBalanceRecord;
import com.evol.service.business.MovieService;
import com.evol.service.order.NetOrdersService;
import com.evol.service.user.UserBalanceRecordService;
import com.evol.web.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping("/test")
@RestController
public class TestController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MovieService movieService;

    @Autowired
    private NetOrdersService netOrdersService;

    @Autowired
    private UserBalanceRecordService userBalanceRecordService;

    @PostMapping("multi")
    public ApiResponse multiTest(){
        Map<String, Object> map = new HashMap<>();

        logger.debug("start query movie");
        Movie movie = movieService.getMovieById(1);
        logger.debug("movie", movie);

        logger.debug("start query orderList");
        List<NetOrders> orderList = netOrdersService.getAllOrderByUserId(1);
        logger.debug("orderList", orderList);

        logger.debug("start query balanceRecordList");
        List<UserBalanceRecord> balanceRecordList = userBalanceRecordService.getAllRecordByUserId(1);
        logger.debug("balanceRecordList", balanceRecordList);

        map.put("movie", movie);
        map.put("orderList", orderList);
        map.put("balanceRecordList", balanceRecordList);
        ApiResponse<Map> resp = ApiResponse.success(map);
        return resp;
    }
}
