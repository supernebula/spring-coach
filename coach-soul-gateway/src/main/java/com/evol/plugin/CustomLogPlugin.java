package com.evol.plugin;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Test;
import org.dromara.soul.common.dto.RuleData;
import org.dromara.soul.common.dto.SelectorData;
import org.dromara.soul.common.enums.PluginEnum;
import org.dromara.soul.common.utils.GsonUtils;
import org.dromara.soul.plugin.api.SoulPluginChain;
import org.dromara.soul.plugin.base.AbstractSoulPlugin;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class CustomLogPlugin extends AbstractSoulPlugin {
    @Override
    protected Mono<Void> doExecute(ServerWebExchange exchange, SoulPluginChain chain, SelectorData selector, RuleData rule) {
        log.debug("--function plugin start--");
//        final String ruleHandle = rule.getHandle();
//
//        final Test test =  GsonUtils.getInstance().fromJson(ruleHandle, Test.class);
//
//        log.debug(test.toString());
        return chain.execute(exchange);
    }

    @Override
    public int getOrder() {
        return 0; //PluginEnum.SPRING_CLOUD.getCode()-2;
    }

    @Override
    public String named(){
        return "customLog";
    }

    @Override
    public Boolean skip(final ServerWebExchange exchange){
        return false;
    }
}
