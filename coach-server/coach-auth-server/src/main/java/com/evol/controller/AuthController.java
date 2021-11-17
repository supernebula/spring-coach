package com.evol.controller;

import com.evol.constant.Constants;
import com.evol.util.RedisClientUtil;
import com.evol.web.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("auth")
@RestController
public class AuthController {

    @Autowired
    private RedisClientUtil redisClientUtil;

    /**
     * 判断当前token是否存在（token是否存在）
     * @param token
     * @return
     */
    @GetMapping("/verify")
    public ApiResponse verify(String token){
        String key = Constants.TOKEN + token;
        Boolean isExist = redisClientUtil.exists(key);
        return ApiResponse.success(isExist);
    }
}
