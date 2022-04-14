package com.evol.plugin;

import com.alibaba.fastjson.JSON;
import com.evol.constant.Constants;
import com.evol.domain.LoginUser;
import com.evol.enums.ApiResponseEnum;
import com.evol.plugin.pojo.OauthTokenRuleDTO;
import com.evol.util.RedisClientUtil;
import com.evol.web.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.dromara.soul.common.dto.RuleData;
import org.dromara.soul.common.dto.SelectorData;
import org.dromara.soul.common.enums.PluginEnum;
import org.dromara.soul.plugin.api.SoulPluginChain;
import org.dromara.soul.plugin.base.AbstractSoulPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

@Component
@Slf4j
public class OauthTokenPlugin extends AbstractSoulPlugin {

    @Autowired
    private RedisClientUtil redisClientUtil;

    @Override
    public int getOrder() {
        return PluginEnum.SPRING_CLOUD.getCode()-3;
    }

    @Override
    public String named(){
        return "oauthToken";
    }

    @Override
    public Boolean skip(final ServerWebExchange exchange){
        return false;
    }

    @Override
    protected Mono<Void> doExecute(ServerWebExchange exchange, SoulPluginChain chain, SelectorData selector,
                                   RuleData rule){
        ServerHttpRequest request = Objects.requireNonNull(exchange).getRequest();
        ServerHttpResponse response = Objects.requireNonNull(exchange).getResponse();
        List<String> authHeaders = request.getHeaders().get("Authorization");
        String token = (authHeaders != null && authHeaders.size() != 0) ? authHeaders.get(0) : null;

        String ruleHandle = rule.getHandle();

        final OauthTokenRuleDTO ruleDto = JSON.toJavaObject(JSON.parseObject(ruleHandle), OauthTokenRuleDTO.class);
        String path = request.getPath().toString();

        //忽略token验证的url，直接放行
        if(ruleDto.getIgnorePathList().contains(path)){
            return chain.execute(exchange);
        }

        //无token，返回无授权
        if(StringUtils.isBlank(token)){
            unauthResponse(response, exchange, chain);
        }

        String key = Constants.TOKEN + token;
        LoginUser loginUser = redisClientUtil.getByKey(key);
        if(loginUser == null){
            return unauthResponse(response, exchange, chain);
        }
        return chain.execute(exchange);
    }

    private Mono<Void> unauthResponse(ServerHttpResponse response, ServerWebExchange exchange, SoulPluginChain chain){

        ApiResponse apiResponse = ApiResponse.fail(ApiResponseEnum.ACCESS_TOKEN_INVALID, "无效的访问token");
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        return response.writeWith(Mono.just(response.bufferFactory().wrap(JSON.toJSONString(apiResponse).getBytes(StandardCharsets.UTF_8))));
    }

}
