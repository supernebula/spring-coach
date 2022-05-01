//package com.evol.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
//
///**
// * 资源服务器
// */
//@Configuration
//@EnableResourceServer
//public class ResourceServerConfigOld extends ResourceServerConfigurerAdapter {
//
////    private static final String RESOURCE_ID = "rest_api";
////
////    @Override
////    public void configure(ResourceServerSecurityConfigurer resources){
////        resources.resourceId(RESOURCE_ID).stateless(false);
////    }
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers(HttpMethod.GET,"/api/**").hasRole("ADMINISTRATOR")
//                .antMatchers("/oauth/confirm_access").permitAll()
//                .antMatchers("/**/*.js").permitAll()
//                .antMatchers("/favicon.ico").permitAll()
//                .and()
//                .requestMatchers().antMatchers("/api/**").and().authorizeRequests().antMatchers("/api/**").authenticated();
//    }
//}
