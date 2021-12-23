package com.evol.controller;

import com.evol.domain.PageBase;
import com.evol.domain.dto.StaffChangePwdDto;
import com.evol.domain.dto.StaffUpsertDto;
import com.evol.domain.model.Staff;
import com.evol.domain.request.StaffQueryRequest;
import com.evol.service.StaffService;
import com.evol.web.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "员工管理")
@RestController
@RequestMapping("/web/staff")
//@CrossOrigin
public class StaffWebController {

    @Autowired
    private StaffService staffService;

    //@CrossOrigin(value = "http://localhost:8080")
    @ApiOperation(value = "添加员工", response = ApiResponse.class)
    @PostMapping("/create")
    public ApiResponse createStaff(StaffUpsertDto param){
        Integer id =  staffService.addStaff(param);
        return ApiResponse.success(id);
    }

    //@CrossOrigin(value = "http://localhost:8080")
    @ApiOperation(value = "删除员工", response = ApiResponse.class)
    @DeleteMapping("/delete")
    public ApiResponse deleteStaff(Integer staffId){
        Integer num = staffService.deleteStaff(staffId);
        return ApiResponse.success(num);
    }

    //@CrossOrigin(value = "http://localhost:8080")
    @ApiOperation(value = "修改员工", response = ApiResponse.class)
    @PostMapping("/update")
    public ApiResponse updateStaff(Integer staffId, StaffUpsertDto param){
        Integer id =  staffService.modifyStaff(staffId, param);
        return ApiResponse.success(id);
    }

    //@CrossOrigin(value = "http://localhost:8080")
    @ApiOperation(value = "修改员工", response = ApiResponse.class)
    @PostMapping("/changePwd")
    public ApiResponse changePwd(Integer staffId, StaffChangePwdDto staffChangePwdDto){
        Integer id =  staffService.changePassword(staffId, staffChangePwdDto);
        return ApiResponse.success(id);
    }

    //@CrossOrigin(value = "http://localhost:8080")
    @ApiOperation(value = "查询员工记录", response = ApiResponse.class)
    @GetMapping("/query")
    public ApiResponse queryStaff(StaffQueryRequest reqParam){
        PageBase<Staff> pageList = staffService.queryPage(reqParam);
        return ApiResponse.success(pageList);
    }

    //@CrossOrigin(value = "http://localhost:8080")
    @ApiOperation(value = "获取员工", response = ApiResponse.class)
    @GetMapping("/get")
    public ApiResponse getStaff(Integer staffId){
        Staff staff = staffService.getStaff(staffId);
        return ApiResponse.success(staff);
    }
}
