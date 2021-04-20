package com.evol.controller;

import com.evol.domain.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "员工管理")
@RestController
@RequestMapping("/web/staff")
public class StaffWebController {
    @ApiOperation(value = "添加员工", response = ApiResponse.class)
    @PostMapping("/addStaff")
    public ApiResponse addStaff(){
        return null;
    }

    @ApiOperation(value = "删除员工", response = ApiResponse.class)
    @PostMapping("/deleteStaff")
    public ApiResponse deleteStaff(){
        return null;
    }

    @ApiOperation(value = "修改员工", response = ApiResponse.class)
    @PostMapping("/modifyStaff")
    public ApiResponse modifyStaff(){
        return null;
    }

    @ApiOperation(value = "查询员工记录", response = ApiResponse.class)
    @GetMapping("/queryStaff")
    public ApiResponse queryStaff(String key, Integer page, Integer pageSize){
        return null;
    }

    @ApiOperation(value = "获取员工", response = ApiResponse.class)
    @PostMapping("/getStaff")
    public ApiResponse getStaff(Integer movieId){
        return null;
    }

}
