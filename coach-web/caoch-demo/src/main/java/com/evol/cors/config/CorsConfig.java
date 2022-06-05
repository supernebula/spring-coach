package com.evol.cors.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * CORS方式一
 * spring-webmvc-5.2.0R
 * springboot2.2.0R之前的版本，这种方式弊端，如果自定义拦截器，跨域配置会失效。执行顺序先处理拦截器，在执行请求映射逻辑
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  //指定可以跨域的路径
                .allowedHeaders("*")           //服务器允许的请求头
                .allowedMethods("POST", "PUT", "GET", "OPTIONS", "DELETE") //服务器允许的请求方法
                .allowCredentials(true)         //允许带cookie的跨域请求Access-Control-Allow-Credentials
                .allowedOrigins("*")            //"*" 表示服务端允许所有的访问请求
                .maxAge(3600);                  //预检请求的缓存时间，单位s，默认1800s
    }
}
