package com.evol.controller;

import com.evol.constant.Constants;
import com.evol.util.RedisClientUtil;
import com.evol.web.ApiResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.feed.AtomFeedHttpMessageConverter;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.concurrent.TimeUnit;

@RequestMapping("auth")
@RestController
public class AuthController {

    @Autowired
    private RedisClientUtil redisClientUtil;

    /**
     * 判断当前token是否存在（token是否存在）
     * @return
     */
    //@CrossOrigin(value = "http://localhost:8080")
    @GetMapping("/verify")
    public ApiResponse verify(ServletWebRequest request){
        String token = request.getHeader("Authorization");
        if(StringUtils.isBlank(token)){
            token = request.getParameter("token");
        }
        String key = Constants.TOKEN + token;

        Boolean isExist = redisClientUtil.exists(key);
        if(isExist){
            redisClientUtil.expire(key, 1, TimeUnit.HOURS);
        }
        return ApiResponse.success(isExist);
    }
}
