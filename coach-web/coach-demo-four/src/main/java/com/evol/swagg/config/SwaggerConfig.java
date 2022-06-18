package com.evol.swagg.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2 //声明启动Swagger2
//声明属性是否可用
@ConditionalOnProperty(name = "spring.swagger.enable", havingValue = "true", matchIfMissing = true)
public class SwaggerConfig {
    //注入Docket, 添加扫描的包路径
    @Bean
    public Docket customDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
                //ApiInfo用户描述API文件的基础信息
                .apiInfo(new ApiInfoBuilder()
                        //标题
                        .title("Swagger2 文档")
                        //描述
                        .description("Rest风格文档")
                        //版本号
                        .version("1.0.0")
                        .build()).select()
                //定义扫描的Swagger接口包路径
                .apis(RequestHandlerSelectors.basePackage("com.evol.swagg.controller"))
                //所有路径都满足这个条件
                .paths(PathSelectors.any()).build();
    }
}
