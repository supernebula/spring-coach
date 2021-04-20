package com.evol.controller;

import com.evol.domain.PageBase;
import com.evol.domain.model.UserBalanceRecord;
import com.evol.service.UserBalanceRecordService;
import com.evol.web.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "用户余额变动管理")
@RequestMapping("/userBalanceRecord")
@RestController
public class UserBalanceRecordWebController {

    @Autowired
    UserBalanceRecordService userBalanceRecordService;

    @ApiOperation(value = "根据ID获取余额记录", response = ApiResponse.class)
    @GetMapping("getUser")
    public ApiResponse getRecord(Integer id){
        UserBalanceRecord record = userBalanceRecordService.getBalanceRecordById(id);
        return ApiResponse.success(record);
    }

    @ApiOperation(value = "分页查询", response = ApiResponse.class)
    @GetMapping("queryRecord")
    public ApiResponse queryRecord(Integer userId, Integer pageNo, Integer pageSize){
        //分页查询
        PageBase<UserBalanceRecord> pageResult = userBalanceRecordService.queryBalanceRecord(userId, pageNo, pageSize);
        return ApiResponse.success(pageResult);
    }

}
