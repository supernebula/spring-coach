package com.evol.controller;

import com.evol.domain.PageBase;
import com.evol.domain.dto.UserAddDto;
import com.evol.domain.dto.UserModifyDto;
import com.evol.domain.model.User;
import com.evol.domain.request.UserQueryRequest;
import com.evol.service.UserService;
import com.evol.web.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "用户管理")
@RequestMapping("/web/user")
@RestController
//@CrossOrigin
public class UserWebController {

    @Autowired
    UserService userService;

    //@CrossOrigin(value = "http://localhost:8080")
    @ApiOperation(value = "查询用户", response = ApiResponse.class)
    @GetMapping("query")
    public ApiResponse Query(UserQueryRequest userQueryRequest){
        PageBase<User> pageList = userService.queryPage(userQueryRequest);
        return ApiResponse.success(pageList);
    }

    //@CrossOrigin(value = "http://localhost:8080")
    @ApiOperation(value = "添加用户", response = ApiResponse.class)
    @PostMapping("create")
    public ApiResponse Add(UserAddDto userAddDto){
        Integer id = userService.AddUser(userAddDto);
        return ApiResponse.success(id);
    }

    //@CrossOrigin(value = "http://localhost:8080")
    @ApiOperation(value = "修改用户", response = ApiResponse.class)
    @PostMapping("update")
    public ApiResponse Update(UserModifyDto userModifyDto){
        Integer id = userService.ModifyUser(userModifyDto);
        return ApiResponse.success(id);
    }

    //@CrossOrigin(value = "http://localhost:8080")
    @ApiOperation(value = "获取用户", response = ApiResponse.class)
    @GetMapping("get")
    public ApiResponse getUser(Integer userId){
        User user = userService.getUserById(userId);
        return ApiResponse.success(user);
    }

    //@CrossOrigin(value = "http://localhost:8080")
    @ApiOperation(value = "删除用户", response = ApiResponse.class)
    @DeleteMapping("delete")
    public ApiResponse deleteUser(Integer userId){
        userService.deleteUserById(userId);
        return ApiResponse.success(userId);
    }


}
