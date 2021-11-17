//package com.evol.filter;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.dromara.soul.web.filter.AbstractWebFilter;
//import org.springframework.web.server.ServerWebExchange;
//import org.springframework.web.server.WebFilterChain;
//import reactor.core.publisher.Mono;
//
//public class ElapseTimeFilter extends AbstractWebFilter {
//
//    private static final Log log = LogFactory.getLog(ElapseTimeFilter.class);
//
//    private static final String START_STAMP = "startStamp";
//
//    @Override
//    protected Mono<Boolean> doFilter(ServerWebExchange exchange, WebFilterChain chain) {
//
//        exchange.getAttributes().put(START_STAMP, System.currentTimeMillis());
//
//
////        chain.filter(exchange);
////        return new Mono<Boolean>(true);
//
//
//    }
//
//    @Override
//    protected Mono<Void> doDenyResponse(ServerWebExchange exchange) {
//
//
//        return null;
//
//
//
//    }
//
//    private void printDurationTime(ServerWebExchange exchange) {
//        long startStamp = exchange.getAttribute(START_STAMP);
//        long endStamp = System.currentTimeMillis();
//        log.debug("duration filter time : " + (endStamp - startStamp) + " ms");
//
//
//    }
//}
