package com.evol.controller;

import com.evol.util.JwtUtil;
import com.evol.web.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private JwtUtil jwtUtil;

    public ApiResponse login(String username, String password){
        String userId = "123456";
        String token = jwtUtil.generateToken(userId);
        return ApiResponse.success(token);
    }

}
