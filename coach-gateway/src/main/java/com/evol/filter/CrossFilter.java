//package com.evol.filter;
//
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.core.Ordered;
//import org.springframework.core.annotation.Order;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.http.server.reactive.ServerHttpResponse;
//import org.springframework.stereotype.Component;
//import org.springframework.web.cors.reactive.CorsUtils;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
//
//@Component
//@Order(-99)
//public class CrossFilter implements GlobalFilter, Ordered {
//
//    private static final String ALLOWED_HEADERS = "x-requested-with, authorization, Content-Type, Authorization, credential, X-XSRF-TOKEN,token,username,client";
//
//    private static final String ALLOWED_METHODS = "*";
//
//    private static final String ALLOWED_ORIGIN = "*";
//
//    private static final String ALLOWED_EXPOSE = "*";
//
//    private static final String MAX_AGE = "18000";
//
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        ServerHttpRequest request = exchange.getRequest();
//        if (CorsUtils.isCorsRequest(request)) {
//            ServerHttpResponse response = exchange.getResponse();
//            HttpHeaders headers = response.getHeaders();
//            headers.add("Access-Control-Allow-Origin", ALLOWED_ORIGIN);
//            headers.add("Access-Control-Allow-Methods", ALLOWED_METHODS);
//            headers.add("Access-Control-Max-Age", MAX_AGE);
//            headers.add("Access-Control-Allow-Headers", ALLOWED_HEADERS);
//            headers.add("Access-Control-Expose-Headers", ALLOWED_EXPOSE);
//            headers.add("Access-Control-Allow-Credentials", "true");
//            if (request.getMethod() == HttpMethod.OPTIONS) {
//                response.setStatusCode(HttpStatus.OK);
//                return Mono.empty();
//            }
//        }
//        return chain.filter(exchange);
//    }
//
//    @Override
//    public int getOrder() {
//
//        return -99;
//    }
//}
