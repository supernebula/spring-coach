package com.evol.interceptor.config;

import com.evol.interceptor.FirstHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 创建配置类，将拦截器注入容器
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    public void addInterceptors(InterceptorRegistry registry) {
        FirstHandlerInterceptor firstHandlerInterceptor = new FirstHandlerInterceptor();
        registry.addInterceptor(firstHandlerInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/free");
        //SecondHandlerInterceptor secondHandlerInterceptor = new SecondHandlerInterceptor();
        //registry.addInterceptor(secondHandlerInterceptor).addPathPatterns("/**");
    }
}
