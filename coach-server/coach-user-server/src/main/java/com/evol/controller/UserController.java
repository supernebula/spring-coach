package com.evol.controller;

import com.evol.domain.dto.UserAddDto;
import com.evol.domain.dto.UserModifyDto;
import com.evol.domain.model.User;
import com.evol.service.UserService;
import com.evol.web.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("addUser")
    public ApiResponse<Integer> Add(UserAddDto userAddDto){
        Integer id = userService.AddUser(userAddDto);
        return ApiResponse.success(id);
    }

    @PostMapping("modifyUser")
    public ApiResponse<Integer> Modify(UserModifyDto userModifyDto){
        Integer id = userService.ModifyUser(userModifyDto);
        return ApiResponse.success(id);
    }

    @GetMapping("getUser")
    public ApiResponse<User> getUser(Integer userId){
        User user = userService.getUserById(userId);
        return ApiResponse.success(user);
    }

    @DeleteMapping("deleteUser")
    public ApiResponse<Integer> deleteUser(Integer userId){
        userService.deleteUserById(userId);
        return ApiResponse.success(userId)
    }

}
