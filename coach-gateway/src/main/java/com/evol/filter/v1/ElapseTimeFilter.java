package com.evol.filter.v1;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

//note
public class ElapseTimeFilter  implements GlobalFilter, Ordered {

    private static final Log log = LogFactory.getLog(ElapseTimeFilter.class);

    private static final String START_STAMP = "startStamp";
    /**
     * 请求响应时长统计
     * 1.首先将请求进来时的时间戳写入到 exchange中
     * 2.filter链走完后，无论成功或者失败，都视为完成，从exchange取出开始时间戳，打印时长信息
     * @param exchange the current server exchange
     * @param chain provides a way to delegate to the next filter
     * @return mono
     **/
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.debug("duration statistics filter");

        exchange.getAttributes().put(START_STAMP, System.currentTimeMillis()); return chain.filter(exchange)
                .doFinally(f -> printDurationTime(exchange));
    } private void printDurationTime(ServerWebExchange exchange) {
        long startStamp = exchange.getAttribute(START_STAMP);
        long endStamp = System.currentTimeMillis();
        log.debug("duration filter time : " + (endStamp - startStamp) + " ms");


    }


//    private static final String ELAPSED_TIME_BEGIN = "elapsedTimeBegin";
//
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//
//        exchange.getAttributes().put(ELAPSED_TIME_BEGIN, System.currentTimeMillis());
//        return chain.filter(exchange).then(
//                Mono.fromRunnable(() -> {
//                    Long startTime = exchange.getAttribute(ELAPSED_TIME_BEGIN);
//                    if (startTime != null) {
//                        System.out.println(exchange.getRequest().getURI().getRawPath() + ": " + (System.currentTimeMillis() - startTime) + "ms");
//                    }
//                })
//        );
//
//    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
        //return Ordered.WRITE_RESPONSE_FILTER_ORDER - 1;
    }
}

