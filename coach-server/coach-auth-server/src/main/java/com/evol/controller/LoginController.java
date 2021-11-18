package com.evol.controller;

import com.evol.constant.Constants;
import com.evol.domain.dto.LoginParam;
import com.evol.domain.dto.LoginUser;
import com.evol.domain.dto.StaffToken;
import com.evol.domain.model.Staff;
import com.evol.enums.UserType;
import com.evol.service.StaffService;
import com.evol.util.JsonUtil;
import com.evol.util.JwtUtil;
import com.evol.util.RedisClientUtil;
import com.evol.web.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.security.krb5.internal.PAData;

@RestController
public class LoginController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RedisClientUtil redisClientUtil;

    @Autowired
    private StaffService staffService;


    @CrossOrigin(value = "http://localhost:8090")
    @PostMapping("/login")
    public ApiResponse login(LoginParam param){
        Staff staff = staffService.login(param);
        LoginUser loginUser = new LoginUser();
        loginUser.setId(staff.getId());
        loginUser.setLoginName(staff.getLoginName());
        String token = jwtUtil.generateToken(UserType.STAFF.getCode() + "_" + loginUser.getId());
        redisClientUtil.add(Constants.TOKEN + token, loginUser);
        return ApiResponse.success(token);
    }


    @CrossOrigin(value = "http://localhost:8090")
    @PostMapping("/logout")
    public ApiResponse logout(String token){
        redisClientUtil.deleteByKeys(Constants.TOKEN + token);
        return ApiResponse.success(0);
    }
}
