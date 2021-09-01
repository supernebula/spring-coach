//package com.evol.config;

//import com.evol.coach.filter.ElapseTimeFilter;
//import org.springframework.cloud.gateway.route.RouteLocator;
//import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class RoutesConfig {
//

//    @Bean  //note
//public RouteLocator routeLocator(RouteLocatorBuilder builder) {
//    return builder.routes().route(r ->
//            r.path("/aa")
//                    //转发路由
//                    .uri("http://localhost:8003/provider/test")
//                    //注册自定义过滤器
//                    .filters(new ElapseTimeFilter())
//                    //给定id
//                    .id("user-service"))
//            .build();
//}
//
//
//
////    /**
////     * 全局filter
////     * @return
////     */
////    @Bean
////    public ElapseTimeFilter myGlobalFilter() {
////        return new ElapseTimeFilter();
////    }
//
//}
