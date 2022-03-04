package com.evol.interceptor;

import com.evol.util.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.logging.Handler;

@Slf4j
@Component
public class TokenInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1. 从请求头种获取token
        String token = request.getHeader("token");
        // 2. 判断 token 是否存在
        if (StringUtils.isBlank(token)) {
            log.info("未登录");
            throw new TokenRuntimeException("未登录");
        }

        //3. 解析token
        Claims claim = jwtUtil.getClaimsByToken(token);
        if (claim == null) {
            log.error("token 解析错误");
            //抛出自定义异常
            throw new TokenRuntimeException("token 解析错误");
        }

        //4、判断 token 是否过期
        Date expiration = claim.getExpiration();
        boolean tokenExpired = jwtUtil.isTokenExpired(expiration);
        if (tokenExpired) {
            log.error("token已过期，请重新登录");
            // 这里可以自定义 抛出 token 异常
            throw new TokenRuntimeException("token已过期，请重新登录");
        }

        // 5、 从 token 中获取员工信息
        String subject = claim.getSubject();

        // 6、去数据库中匹配 id 是否存在 (这里直接写死了)
        if (null == subject) {
            System.out.println("员工不存在");
            // 这里可以自定义 抛出 token 异常

            throw new TokenRuntimeException("员工不存在");
        }
        // 7、成功后 设置想设置的属性，比如员工姓名
        request.setAttribute("userId", subject);
        request.setAttribute("userName", "张三");
        return true;

    }
}
