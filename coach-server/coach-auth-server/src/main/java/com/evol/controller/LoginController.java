package com.evol.controller;

import com.evol.constant.Constants;
import com.evol.domain.dto.LoginParam;
import com.evol.domain.dto.LoginUser;
import com.evol.service.StaffService;
import com.evol.util.JwtUtil;
import com.evol.util.RedisClientUtil;
import com.evol.web.ApiResponse;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RedisClientUtil redisClientUtil;

    @Autowired
    private StaffService staffService;


    //@CrossOrigin(value = "http://localhost:8080")
    @PostMapping("/login")
    public ApiResponse<LoginUser> login(LoginParam param){
        return staffService.login(param);
    }


    //@CrossOrigin(value = "http://localhost:8080")
    @PostMapping("/logout")
    public ApiResponse logout(String token){
        redisClientUtil.deleteByKeys(Constants.TOKEN + token);
        return ApiResponse.success(0);
    }

    //@CrossOrigin(value = "http://localhost:8080")
    @GetMapping("/userInfo")
    public ApiResponse<LoginUser> userInfo(String token){
        Claims claims = jwtUtil.getClaimsByToken(token);
        String subject = claims.getSubject();
        LoginUser loginUser = redisClientUtil.getByKey(Constants.TOKEN + token);
        return ApiResponse.success(loginUser);
    }
}
