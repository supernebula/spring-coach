package com.evol.config;

import com.evol.filter.JWTAuthenticationFilter;
import com.evol.filter.JWTAuthorizationFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * Spring Security的权限管理方式
 * WebSecurityConfigurerAdapter 是旧的方式，新的方式采用 filterChain ，see  https://www.cnblogs.com/felordcn/p/15922976.html
 * （1） Url权限控制
 * @author admin
 */
@Configuration
@EnableWebSecurity //开关注解，开启Security
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true) //保证post之前的注解可以使用
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * 用于配置全局认证相关的信息
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 用于全局请求忽略规则配置，比如一些静态文件，注册登录页面的放行
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }



    /**
     * 用于具体的权限控制规则配置
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
                // 设置URL的授权
                http.authorizeRequests()                // 这里需要将登录页面放行
                        //除了上面，其他所有请求必须被认证
                        .antMatchers("/resources/**", "/favicon.ico", "/login")
                        .permitAll().and()
                .formLogin()
                //登录页面
                .loginPage("/login")
                //登录成功后的页面
                .successForwardUrl("/index")
                //登录失败后的页面
                .failureForwardUrl("/failure")
                        //successHandler\failureHandler会因为设置了其他过滤器，而失效
                .successHandler((req, resp, auth) -> {
                    log.debug(req.getRequestURI());
                    log.debug(resp.getStatus() + "");
                    log.debug(auth.getName(), auth.getPrincipal());

//                    System.out.println(req.getRequestURI());
//                    System.out.println(resp.getStatus() + "");
//                    System.out.println(auth.getPrincipal());

                })
                .failureHandler((req, resp, ex) -> {
                    log.debug(req.getRequestURI());
                    log.debug(resp.getStatus() + "");
                    log.debug(ex.getMessage(), ex);
                })
                .and()
                .logout()
                .and()
                // 添加JWT登录拦截器
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                // 添加JWT鉴权拦截器
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                .sessionManagement()
                // 设置Session的创建策略为：Spring Security永不创建HttpSession 不使用HttpSession来获取SecurityContext
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    //配置不需要CSRF保护的URL
//                .ignoringAntMatchers("/logout")
//                .ignoringAntMatchers("/user/add")
                .and()
                // 关闭csrf
                .csrf().disable();// 禁用 Spring Security 自带的跨域处理，因为不使用session
    }

    /**
     * 跨域配置
     * @return 基于URL的跨域配置信息
     */
    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        //注册跨域配置
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

    /**
     * 1 表单登录
     * @param http
     * @throws Exception
     */
    //@Override
    protected void configure1(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .antMatchers("/resources/**", "/favicon.ico", "/login", "/logout")
                .permitAll().and()
                .formLogin() // 配置表单登录
                //登录页面
                .loginPage("/login")
                //登录成功后的页面
                .successForwardUrl("/index")
                //.defaultSuccessUrl("/index")  // 未指定地址时，默认defaultSuccessUrl
                //登录失败后的页面
                .failureForwardUrl("/failure") //登录失败服务端跳转
                .failureUrl("/failure") //登录失败重定向

                .usernameParameter("name")
                .passwordParameter("password")
                .successHandler((req, resp, auth) -> {
                    //自定义登录成功后的结果处理
                    // https://zhuanlan.zhihu.com/p/92698964
                })
                .failureHandler((req, resp, e) -> {
                    //自定义登录失败后的结果处理
                })
                .and() //注销的逻辑
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("afterlogout.html")
                .deleteCookies("JSESSIONID");
    }
}