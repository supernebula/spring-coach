//package com.evol.coach.config;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import reactor.core.publisher.Mono;
//
//@Configuration  //note
//public class RateLimiterConfiguration {
//
//    private static final Logger logger = LoggerFactory.getLogger(RateLimiterConfiguration.class);
//
//    @Bean(value = "remoteUrlPathKeyResolver")
//    KeyResolver remoteAddrKeyResolver() {
//
//        return exchange -> {
//            String path = exchange.getRequest().getURI().getPath();
//            logger.debug(path);
//            return Mono.just(exchange.getRequest().getURI().getPath());
//        };
//    }
//
//}
