package com.evol.controller;

import com.evol.base.client.UserDTO;
import com.evol.domain.dto.UserBalanceDTO;
import com.evol.domain.model.User;
import com.evol.enums.ApiResponseEnum;
import com.evol.service.UserService;
import com.evol.web.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api(tags = "内部接口用户列表测试")
@RequestMapping("/inner/user")
@RestController
public class innerUserController {

    @Autowired
    private UserService userService;

    @GetMapping("/get")
    public ApiResponse<UserDTO> get(@RequestParam(value = "userId") Integer userId){
        User user = userService.getUserById(userId);
        return user != null ? ApiResponse.success(user) : ApiResponse.fail(ApiResponseEnum.NO_RECORD, "参数错误，找不到指定的用户");
    }


    @ApiOperation(value = "查询用户列表",notes = "查询用户列表")
    @GetMapping("list")
    public List<UserDTO> list(){
        List<UserDTO> list = new ArrayList<UserDTO>();
        list.add(new UserDTO(1, "name1", "13688885555", "地址1"));
        list.add(new UserDTO(1, "name2", "17811113333", "地址2"));
        list.add(new UserDTO(1, "name3", "13814244445", "地址3"));
        return list;
    }

    @ApiOperation(value = "查询用户列表",notes = "查询用户列表")
    @GetMapping("listTime")
    public List<UserDTO> listTime() throws InterruptedException {
        List<UserDTO> list = new ArrayList<UserDTO>();
        list.add(new UserDTO(1, "nameTime1", "13688885555", "地址Time1"));
        list.add(new UserDTO(1, "nameTime2", "17811113333", "地址Time2"));
        list.add(new UserDTO(1, "nameTime3", "13814244445", "地址Time3"));
        Thread.sleep(1000);
        return list;
    }

    @PostMapping("upsert")
    public UserDTO upsert(UserDTO user, Long userId){
        if(user == null){
            return  null;
        }
        user.setAddress(user.getAddress() + "_upsert");
        return user;
    }

    @GetMapping("queryUserBalance")
    public UserBalanceDTO queryUserBalance(Integer userId){
        return userService.queryBalance(userId);
    }


}
