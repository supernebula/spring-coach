package com.evol.coach.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.evol.constant.Constants;
import com.evol.enums.ApiResponseEnum;
import com.evol.util.RedisClientUtil;
import com.evol.web.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

@Slf4j
public class AuthFilter {

    private static final String[] whiteList = {"/auth/login", "/user/register"};

//    @Resource(name = "stringRedisTemplate")
//    private ValueOperations<String, String> ops;

    @Autowired
    private RedisClientUtil redisClientUtil;

    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain){
        String url = exchange.getRequest().getURI().getPath();
        log.info("url:{}", url);
        //跳过不需要验证的路径
        if(Arrays.asList(whiteList).contains(url)){
            return chain.filter(exchange);
        }

        String token = exchange.getRequest().getHeaders().getFirst(Constants.TOKEN);
        //token为空
        if(StringUtils.isBlank(token)){
            return setUnauthorizedResponse(exchange, "token can t null or empty string");
        }

        //String userStr = ops.get(Constants.ACCESS_TOKEN + token);
        //String userStr = ops.get(Constants.ACCESS_TOKEN + token);
        String userStr = redisClientUtil.get(Constants.ACCESS_TOKEN + token);
        if (org.apache.commons.lang3.StringUtils.isBlank(userStr))
        {
            return setUnauthorizedResponse(exchange, "token verify error");
        }
        JSONObject json = JSONObject.parseObject(userStr);
        String userId = json.getString("userId");
        //查询token信息
        if(StringUtils.isBlank(userId)){
            return setUnauthorizedResponse(exchange, "token verify error");
        }

        // 设置userId到request里，后续根据userId，获取用户信息
        ServerHttpRequest mutableReq = exchange.getRequest().mutate().header(Constants.CURRENT_ID, userId)
                .header(Constants.CURRENT_USERNAME, json.getString("loginName")).build();

        ServerWebExchange mutableExchange = exchange.mutate().request(mutableReq).build();
        return chain.filter(mutableExchange);


    }


    private Mono<Void> setUnauthorizedResponse(ServerWebExchange exchange, String msg){
        ServerHttpResponse originalResponse = exchange.getResponse();
        originalResponse.setStatusCode(HttpStatus.UNAUTHORIZED);
        originalResponse.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        byte[] response = null;
        try
        {
            //response = JSON.toJSONString(R.error(401, msg)).getBytes(Constants.UTF8);
            response = JSON.toJSONString(ApiResponse.fail(ApiResponseEnum.NOT_FOUND)).getBytes(Constants.UTF8);
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        DataBuffer buffer = originalResponse.bufferFactory().wrap(response);
        return originalResponse.writeWith(Flux.just(buffer));
    }

    //@Override
    public int getOrder()
    {
        return -200;
    }
}
