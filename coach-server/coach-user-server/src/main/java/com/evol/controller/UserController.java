package com.evol.controller;

import com.evol.domain.dto.UserAddDto;
import com.evol.domain.dto.UserModifyDto;
import com.evol.domain.model.User;
import com.evol.service.UserService;
import com.evol.web.ApiResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @ApiOperation(value = "添加用户", response = ApiResponse.class)
    @PostMapping("addUser")
    public ApiResponse Add(UserAddDto userAddDto){
        Integer id = userService.AddUser(userAddDto);
        return ApiResponse.success(id);
    }

    @ApiOperation(value = "修改用户", response = ApiResponse.class)
    @PostMapping("modifyUser")
    public ApiResponse Modify(UserModifyDto userModifyDto){
        Integer id = userService.ModifyUser(userModifyDto);
        return ApiResponse.success(id);
    }

    @ApiOperation(value = "获取用户", response = ApiResponse.class)
    @GetMapping("getUser")
    public ApiResponse getUser(Integer userId){
        User user = userService.getUserById(userId);
        return ApiResponse.success(user);
    }

    @ApiOperation(value = "删除用户", response = ApiResponse.class)
    @DeleteMapping("deleteUser")
    public ApiResponse deleteUser(Integer userId){
        userService.deleteUserById(userId);
        return ApiResponse.success(userId);
    }

}
