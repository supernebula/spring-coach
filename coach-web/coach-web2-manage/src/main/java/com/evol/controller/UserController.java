package com.evol.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.evol.domain.dto.LoginParam;
import com.evol.domain.model.Staff;
import com.evol.service.StaffService;
import com.evol.web.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/")
public class UserController {


    @Autowired
    private StaffService staffService;

    @RequestMapping("/login")
    public ApiResponse<Staff> login(String username, String password){
        Staff staff = staffService.login(username, password);
        StpUtil.login(staff.getLoginName());
        return ApiResponse.success(staff);
    }

    // 测试登录，浏览器访问： http://localhost:8081/user/doLogin?username=zhang&password=123456
    @RequestMapping("doLogin")
    public String doLogin(String username, String password) {
        // 此处仅作模拟示例，真实项目需要从数据库中查询数据进行比对
        if("zhang".equals(username) && "123456".equals(password)) {
            StpUtil.login(10001);
            return "登录成功";
        }
        return "登录失败";
    }

    // 查询登录状态，浏览器访问： http://localhost:8081/user/isLogin
    @RequestMapping("isLogin")
    public String isLogin() {
        return "当前会话是否登录：" + StpUtil.isLogin();
    }

    @RequestMapping("checkLogin")
    public void checkLogin() {
        StpUtil.checkLogin();
    }

    @RequestMapping("logout")
    public void logout() {
        StpUtil.logout();
    }

    @RequestMapping("getLoginIdByToken")
    public Object getLoginIdByToken(String tokenValue) {
        return StpUtil.getLoginIdByToken(tokenValue);
    }

    @RequestMapping("getTokenName")
    public String getTokenName() {
        return StpUtil.getTokenName();
    }

    @RequestMapping("getTokenValue")
    public String getTokenValue()
    {
        return StpUtil.getTokenValue();
    }

    @RequestMapping("getTokenInfo")
    public Object getTokenInfo() {
        return StpUtil.getTokenInfo();
    }





}
