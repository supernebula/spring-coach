//package com.evol.restdata.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
//import org.springframework.data.rest.core.mapping.RepositoryDetectionStrategy;
//import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
//
//@Configuration
//public class CustomRestMvcConfiguration {
//
//    @Bean
//    public RepositoryRestConfigurer repositoryRestConfigurer(){
//        RepositoryRestConfigurer repositoryRestConfigurer = new RepositoryRestConfigurer() {
//            public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
//                config.setBasePath("/api");  //将基本路径更改为 /api
//                //默认为DEFAULT,现修改为ALL
//                config.setRepositoryDetectionStrategy(RepositoryDetectionStrategy.RepositoryDetectionStrategies.ALL);
//            }
//        };
//        return repositoryRestConfigurer;
//    }
//}
