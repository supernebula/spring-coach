package com.evol.config;

import com.alibaba.fastjson.JSON;
import com.evol.domain.dto.AccountDetails;
import com.evol.domain.dto.LoginUserVO;
import com.evol.enums.ApiResponseEnum;
import com.evol.filter.JWTAuthenticationFilter;
import com.evol.filter.JWTAuthorizationFilter;
import com.evol.util.JwtTokenUtil;
import com.evol.web.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collection;

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
    //@Override
    protected void configure1(HttpSecurity http) throws Exception {
                // 设置URL的授权
                http.authorizeRequests()                // 这里需要将登录页面放行
                        //除了上面，其他所有请求必须被认证
                        .antMatchers("/resources/**", "/favicon.ico", "/login")
                        .permitAll().and()
                .formLogin() // 需要启用session
                //登录页面
                .loginPage("/login")
                .defaultSuccessUrl("/index", true) //浏览器跳转
                //登录成功后的页面
//                .successForwardUrl("/index") //服务端重定向
                //登录失败后的页面
                .failureForwardUrl("/failure")
                        //successHandler\failureHandler会因为设置了其他过滤器，而失效
//                .successHandler((req, resp, auth) -> {
//                    //加塞逻辑 或 替代 successForwardUrl 返回JSON
//                    log.debug(req.getRequestURI());
//                    log.debug(resp.getStatus() + "");
//                    log.debug(auth.getName(), auth.getPrincipal());
//
//                    Object obj = auth.getPrincipal();
//                    AccountDetails user = (AccountDetails)obj;
//
//                    // 从User中获取权限信息
//                    Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
//                    //创建Token
//                    String token = JwtTokenUtil.createToken(user.getUsername(), authorities.toString());
//                    //设置编码 防止乱码问题
//                    resp.setCharacterEncoding("UTF-8");
//                    resp.setContentType("application/json; charset=utf-8");
//                    //在请求头里返回创建成功的token
//                    //设置请求头为带有 “”前缀的token字符串
//                    resp.setHeader("token", JwtTokenUtil.TOKEN_PREFIX + token);
//                    // 处理编码方式 防止中文乱码
//                    resp.setContentType("text/json;charset=utf-8");
//                    // 将反馈塞到HttpServletResponse中返回给前台
//                    LoginUserVO loginUserVO = new LoginUserVO();
//                    loginUserVO.setLoginName(user.getUsername());
//                    loginUserVO.setNickName(user.getNickname());
//                    loginUserVO.setToken(token);
//                    ApiResponse apiResponse = ApiResponse.success(loginUserVO);
//                    resp.getWriter().write(JSON.toJSONString(apiResponse));
//
//                })
//                .failureHandler((req, resp, ex) -> {
//                    //加塞逻辑 或 替代 failureForwardUrl 返回JSON
//                    log.debug(req.getRequestURI());
//                    log.debug(resp.getStatus() + "");
//                    log.debug(ex.getMessage(), ex);
//
//                    String msg = "";
//                    //账号过期
//                    if(ex instanceof AccountExpiredException){
//                        msg = "账号已过期";
//                    }
//                    else if(ex instanceof BadCredentialsException){
//                        msg = "密码错误";
//                    }
//                    else if(ex instanceof CredentialsExpiredException){
//                        msg = "密码已过期";
//                    }// 账号不可用
//                    else if (ex instanceof DisabledException) {
//                        msg="账号不可用";
//                    }
//                    //账号锁定
//                    else if (ex instanceof LockedException) {
//                        msg="账号已锁定";
//                    }
//                    // 用户不存在
//                    else if (ex instanceof InternalAuthenticationServiceException) {
//                        msg="用户不存在";
//                    }
//                    // 其他错误
//                    else{
//                        msg="未知异常";
//                    }
//                    // 处理编码方式 防止中文乱码
//                    resp.setContentType("text/json;charset=utf-8");
//                    // 将反馈塞到HttpServletResponse中返回给前台
//                    ApiResponse apiResponse = ApiResponse.fail(ApiResponseEnum.USER_DEFINED_ERROR.getCode(), msg);
//                    resp.getWriter().write(JSON.toJSONString(apiResponse));
//                })
                .and()
                .logout()
                .and()
//                // 添加JWT登录拦截器
//                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
//                // 添加JWT鉴权拦截器
//                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                   //在formlogin如果启用此行，session将被禁用，造成SecurityContextHolder.getContext().getAuthentication().getPrincipal()只能获取匿名用户，而非登录用户
//                .sessionManagement()
//                // 设置Session的创建策略为：Spring Security永不创建HttpSession 不使用HttpSession来获取SecurityContext
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    //配置不需要CSRF保护的URL
//                .ignoringAntMatchers("/logout")
//                .ignoringAntMatchers("/user/add")
                //.and()
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
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        // 设置URL的授权
        http.authorizeRequests()
                //antMatchers 放行的url，除此之外都需要认证
                .antMatchers("/resources/**", "/favicon.ico", "/login").permitAll()
                .and()
                .formLogin() //启用form登录认证 需要启用session
                .loginPage("/login")//登录页面
                .defaultSuccessUrl("/index", true) //浏览器跳转
//                .successForwardUrl("/index") //服务端重定向，登录成功后的页面
                .failureForwardUrl("/failure") //登录失败后的页面
//                .failureUrl("/failure") //登录失败重定
                .usernameParameter("username") // 匹配表单参数
                .passwordParameter("password") // 匹配表单参数
                //返回JSON而不是上述url， successHandler\failureHandler会因为设置了其他过滤器，而失效
                //以下虽然返回会了token，不会起作用，因为依然是form认证的拦截器通过session获取登录信息！！！
                .successHandler((req, resp, auth) -> {
                    //自定义登录成功后的结果处理
                    // https://zhuanlan.zhihu.com/p/92698964
                    //加塞逻辑 或 替代 successForwardUrl 返回JSON
                    log.debug(req.getRequestURI());
                    log.debug(resp.getStatus() + "");
                    log.debug(auth.getName(), auth.getPrincipal());

                    Object obj = auth.getPrincipal();
                    AccountDetails user = (AccountDetails)obj;

                    // 从User中获取权限信息
                    Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
                    //创建Token
                    String token = JwtTokenUtil.createToken(user.getUsername(), authorities.toString());
                    //设置编码 防止乱码问题
                    resp.setCharacterEncoding("UTF-8");
                    resp.setContentType("application/json; charset=utf-8");
                    //在请求头里返回创建成功的token
                    //设置请求头为带有 “”前缀的token字符串
                    resp.setHeader("token", JwtTokenUtil.TOKEN_PREFIX + token);
                    // 处理编码方式 防止中文乱码
                    resp.setContentType("text/json;charset=utf-8");
                    // 将反馈塞到HttpServletResponse中返回给前台
                    LoginUserVO loginUserVO = new LoginUserVO();
                    loginUserVO.setLoginName(user.getUsername());
                    loginUserVO.setNickName(user.getNickname());
                    loginUserVO.setToken(token);
                    ApiResponse apiResponse = ApiResponse.success(loginUserVO);
                    resp.getWriter().write(JSON.toJSONString(apiResponse));

                })
                .failureHandler((req, resp, ex) -> {
                    //自定义登录失败后的结果处理
                    //加塞逻辑 或 替代 failureForwardUrl 返回JSON
                    log.debug(req.getRequestURI());
                    log.debug(resp.getStatus() + "");
                    log.debug(ex.getMessage(), ex);

                    String msg = "";
                    //账号过期
                    if(ex instanceof AccountExpiredException){
                        msg = "账号已过期";
                    }
                    else if(ex instanceof BadCredentialsException){
                        msg = "密码错误";
                    }
                    else if(ex instanceof CredentialsExpiredException){
                        msg = "密码已过期";
                    }// 账号不可用
                    else if (ex instanceof DisabledException) {
                        msg="账号不可用";
                    }
                    //账号锁定
                    else if (ex instanceof LockedException) {
                        msg="账号已锁定";
                    }
                    // 用户不存在
                    else if (ex instanceof InternalAuthenticationServiceException) {
                        msg="用户不存在";
                    }
                    // 其他错误
                    else{
                        msg="未知异常";
                    }
                    // 处理编码方式 防止中文乱码
                    resp.setContentType("text/json;charset=utf-8");
                    // 将反馈塞到HttpServletResponse中返回给前台
                    ApiResponse apiResponse = ApiResponse.fail(ApiResponseEnum.USER_DEFINED_ERROR.getCode(), msg);
                    resp.getWriter().write(JSON.toJSONString(apiResponse));
                })
                .and()
                .logout()
                .and()
                // 关闭csrf
                .csrf().disable();// 禁用 Spring Security 自带的跨域处理，因为不使用session
    }
}