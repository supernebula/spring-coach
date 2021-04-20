package com.evol.controller;

import com.evol.domain.model.UserBalanceRecord;
import com.evol.service.UserBalanceRecordService;
import com.evol.web.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "C端用户API")
@RestController
@RequestMapping("/client/user")
public class UserClientController {

    @Autowired
    UserBalanceRecordService userBalanceRecordService;

    @ApiOperation(value = "根据ID获取余额记录", response = ApiResponse.class)
    @GetMapping("getUser")
    public ApiResponse getRecord(Integer userId){
        UserBalanceRecord record = userBalanceRecordService.getBalanceRecordById(userId);
        return ApiResponse.success(record);
    }

}
