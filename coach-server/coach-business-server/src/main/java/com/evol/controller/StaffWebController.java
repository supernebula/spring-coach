package com.evol.controller;

import com.evol.domain.PageBase;
import com.evol.domain.dto.StaffUpsertDto;
import com.evol.domain.model.Staff;
import com.evol.domain.request.StaffQueryRequest;
import com.evol.service.StaffService;
import com.evol.web.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "员工管理")
@RestController
@RequestMapping("/web/staff")
public class StaffWebController {

    @Autowired
    private StaffService staffService;

    @ApiOperation(value = "添加员工", response = ApiResponse.class)
    @PostMapping("/addStaff")
    public ApiResponse addStaff(StaffUpsertDto param){
        Integer id =  staffService.addStaff(param);
        return ApiResponse.success(id);
    }

    @ApiOperation(value = "删除员工", response = ApiResponse.class)
    @PostMapping("/deleteStaff")
    public ApiResponse deleteStaff(Integer staffId){
        Integer num = staffService.deleteStaff(staffId);
        return ApiResponse.success(num);
    }

    @ApiOperation(value = "修改员工", response = ApiResponse.class)
    @PostMapping("/modifyStaff")
    public ApiResponse modifyStaff(Integer staffId, StaffUpsertDto param){
        Integer id =  staffService.modifyStaff(staffId, param);
        return ApiResponse.success(id);
    }

    @ApiOperation(value = "查询员工记录", response = ApiResponse.class)
    @GetMapping("/queryStaff")
    public ApiResponse queryStaff(StaffQueryRequest reqParam){
        PageBase<Staff> pageList = staffService.queryPage(reqParam);
        return ApiResponse.success(pageList);
    }

    @ApiOperation(value = "获取员工", response = ApiResponse.class)
    @PostMapping("/getStaff")
    public ApiResponse getStaff(Integer staffId){
        Staff staff = staffService.getStaff(staffId);
        return ApiResponse.success(staff);
    }
}
