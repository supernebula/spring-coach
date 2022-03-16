package com.evol.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Spring Security的权限管理方式
 * （1） Url权限控制
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().passwordEncoder(passwordEncoder())
//                .withUser("zhangsan").password(passwordEncoder().encode("123456")).authorities("ADMIN")
//                .and()
//                .withUser("lisi").password(passwordEncoder().encode("123456")).authorities("ORDINARY");
//    }

    //用于配置全局认证相关的信息
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //用于全局请求忽略规则配置，比如一些静态文件，注册登录页面的放行
    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    //用于具体的权限控制规则配置
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                //登录页面
                .loginPage("/login")
                //登录成功后的页面
                .successForwardUrl("/index")
                //登录失败后的页面
                .failureForwardUrl("/failure")
                .successHandler((req, resp, auth) -> {

                })
                .failureHandler((req, resp, e) -> {

                })
                .and()
                // 设置URL的授权
                .authorizeRequests()
                // 这里需要将登录页面放行
                .antMatchers( "/login","/user/test","/user/add").permitAll()
                //除了上面，其他所有请求必须被认证
                .anyRequest().authenticated()

                .and()
                .logout()
                .permitAll()
//                .ignoringAntMatchers("/logout")
//                .ignoringAntMatchers("/user/add")
                .and()
                // 关闭csrf
                .csrf().disable();


    }
}
