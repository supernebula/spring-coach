package com.evol.service.impl;

import com.evol.constant.Constants;
import com.evol.domain.dto.LoginParam;
import com.evol.domain.dto.LoginUser;
import com.evol.domain.model.Staff;
import com.evol.domain.model.StaffExample;
import com.evol.enums.ApiResponseEnum;
import com.evol.enums.UserType;
import com.evol.mapper.StaffMapper;
import com.evol.service.StaffService;
import com.evol.util.JwtUtil;
import com.evol.util.MD5Util;
import com.evol.util.RedisClientUtil;
import com.evol.web.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    private JwtUtil jwtUtil;


    @Autowired
    private RedisClientUtil redisClientUtil;

    @Autowired
    private StaffMapper staffMapper;

    @Override
    public ApiResponse<LoginUser> login(LoginParam param) {

        StaffExample staffExample = new StaffExample();
        staffExample.createCriteria().andLoginNameEqualTo(param.getLoginName());
        List<Staff> items = staffMapper.selectByExample(staffExample);
        if(CollectionUtils.isEmpty(items) || items.get(0) == null){
            return ApiResponse.fail(ApiResponseEnum.NO_RECORD, "用户不存在");
            //throw  new RuntimeException("用户不存在");
        }
        Staff staff = items.get(0);
        String password = MD5Util.MD5(param.getPassword(), "");
        if(!staff.getPassword().equals(password)){
            return ApiResponse.fail(ApiResponseEnum.USER_DEFINED_ERROR, "用户密码错误");
            //throw  new RuntimeException("密码错误");
        }

        LoginUser loginUser = new LoginUser();
        loginUser.setId(staff.getId());
        loginUser.setLoginName(staff.getLoginName());
        loginUser.setRealName(staff.getRealName());
        String token = jwtUtil.generateToken(UserType.STAFF.getCode() + "_" + loginUser.getId());
        String key = Constants.TOKEN + token;
        redisClientUtil.add(key , loginUser);
        redisClientUtil.expire(key, 1, TimeUnit.HOURS);
        loginUser.setToken(token);
        return ApiResponse.success(loginUser);
    }
}
