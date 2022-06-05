package com.evol.cors.config;

import org.apache.catalina.filters.CorsFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

/**
 * 方式二 CorsFilter过滤器,Springboot 没有自动设置，需要手动注讲CorsFilter注入容器。
 * 过滤器先于拦截器执行
 */
@Configuration
public class CorsFilterConfig {
    @Bean
    public FilterRegistrationBean<CorsFilter> coreFilter(){
        FilterRegistrationBean<CorsFilter> corsFilterFilterRegistrationBean = new FilterRegistrationBean<>();
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        //服务端允许的请求头
        corsConfiguration.addAllowedHeader("*");
        //服务端允许的域请求来源
        corsConfiguration.addAllowedOrigin("*");
        //服务器允许的请求方法
        corsConfiguration.setAllowedMethods(Arrays.asList("POST", "PUT", "GET", "OPTIONS", "DELETE"));
        //允许带cookie的跨域请求Access-Control-Allow-Credentials
        corsConfiguration.setAllowCredentials(true);
        //预检请求的客户端缓存时间，单位s，默认1800s
        corsConfiguration.setMaxAge(3600L);
        //指定被跨域的路径
        source.registerCorsConfiguration("/**", corsConfiguration);
        //设置加载顺序为-1，该值越小优先级越高
        corsFilterFilterRegistrationBean.setOrder(-1);
        return corsFilterFilterRegistrationBean;
    }
}
