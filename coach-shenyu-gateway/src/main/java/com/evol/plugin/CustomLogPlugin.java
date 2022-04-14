package com.evol.plugin;

import com.alibaba.fastjson.JSONObject;
import com.evol.plugin.pojo.CustomLogRuleDTO;
import lombok.extern.slf4j.Slf4j;
import org.dromara.soul.common.dto.RuleData;
import org.dromara.soul.common.dto.SelectorData;
import org.dromara.soul.common.enums.PluginEnum;
import org.dromara.soul.plugin.api.SoulPluginChain;
import org.dromara.soul.plugin.base.AbstractSoulPlugin;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class CustomLogPlugin extends AbstractSoulPlugin {


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
    protected Mono<Void> doExecute(ServerWebExchange exchange, SoulPluginChain chain, SelectorData selector, RuleData rule) {

        log.debug("--function CustomLogPlugin plugin start--");
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
