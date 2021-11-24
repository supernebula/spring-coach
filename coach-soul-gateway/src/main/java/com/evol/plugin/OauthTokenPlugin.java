package com.evol.plugin;

import com.alibaba.fastjson.JSONObject;
import com.evol.enums.ApiResponseEnum;
import com.evol.plugin.pojo.CustomLogRuleDTO;
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
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import com.evol.constant.Constants;

import java.util.List;
import java.util.Objects;

@Component
@Slf4j
public class OauthTokenPlugin extends AbstractSoulPlugin {

    @Autowired
    private RedisClientUtil redisClientUtil;

    @Override
    public int getOrder() {
        return PluginEnum.SPRING_CLOUD.getCode()-2;
    }

    @Override
    public String named(){
        return "customLog";
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
        String token = authHeaders.size() == 0 ? null : authHeaders.get(0);
        if(StringUtils.isBlank(token)){

            ServerHttpResponse newResponse =
            return ApiResponse.fail(ApiResponseEnum.ACCESS_TOKEN_INVALID);
            response.
            response.setStatusCode()
        }


        Boolean isExist = redisClientUtil.exists(key);


        String token = exchange.getRequest() .getHeader("Authorization");
        log.debug("--function OauthTokenPlugin plugin start--");
        final String ruleHandle = rule.getHandle();
        log.info(ruleHandle);
        CustomLogRuleDTO ruleDTO = JSONObject.parseObject(ruleHandle, CustomLogRuleDTO.class);
        log.debug("ignorePath:" + ruleDTO.getIgnorePath());
        log.debug("path:" + ruleDTO.getPath());
        //final Test test =  GsonUtils.getInstance().fromJson(ruleHandle, Test.class);

        String currentPath = exchange.getRequest().getPath().pathWithinApplication().value();
        if(ruleDTO == null || ruleDTO.getIgnorePath().equals(currentPath)){
            return chain.execute(exchange);
        }

        //todo: 如果忽略的path，执行业务逻辑
        // 业务逻辑
        log.debug("执行业务逻辑");


        //log.debug(test.toString());
        return chain.execute(exchange);
    }

}
