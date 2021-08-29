package com.evol.controller;

import com.evol.domain.dto.LoginParam;
import com.evol.domain.dto.StaffToken;
import com.evol.domain.model.Staff;
import com.evol.service.StaffService;
import com.evol.util.JsonUtil;
import com.evol.util.JwtUtil;
import com.evol.web.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import sun.security.krb5.internal.PAData;

@RestController
public class LoginController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private StaffService staffService;


//    public ApiResponse login(String username, String password) {
//        String userId = "123456";
//        String token = jwtUtil.generateToken(userId);
//        return ApiResponse.success(token);
//    }

    public ApiResponse login(LoginParam param){
        Staff staff = staffService.login(param);
        StaffToken staffToken = new StaffToken();
        staffToken.setId(staff.getId());
        staffToken.setLoginName(staff.getLoginName());
        String token = jwtUtil.generateToken(JsonUtil.ParseString(staffToken));
        return ApiResponse.success(token);
    }
}
