package com.evol.controller;

import com.evol.domain.model.UserBalanceRecord;
import com.evol.service.UserBalanceRecordService;
import com.evol.web.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "用户余额变动管理")
@RequestMapping("/userBalanceRecord")
@RestController
public class UserBalanceRecordController {

    @Autowired
    UserBalanceRecordService userBalanceRecordService;

    @ApiOperation(value = "获取用户", response = ApiResponse.class)
    @GetMapping("getUser")
    public ApiResponse getRecord(Integer id){
        UserBalanceRecord record = userBalanceRecordService.getBalanceRecordById(id);
        return ApiResponse.success(record);
    }

    @ApiOperation(value = "删除用户", response = ApiResponse.class)
    @DeleteMapping("deleteUser")
    public ApiResponse queryRecord(Integer userId){
        //分页查询
        return ApiResponse.success(null);
    }
}
