package com.evol.filter;

import com.alibaba.fastjson.JSON;
import com.evol.domain.dto.LoginUserVO;
import com.evol.domain.dto.AccountDetails;
import com.evol.enums.ApiResponseEnum;
import com.evol.util.JwtTokenUtil;
import com.evol.web.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter  {

    @Autowired
    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager){
        this.authenticationManager = authenticationManager;
    }

    /**
     * 验证操作 接收解析用户凭证
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }

    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,FilterChain chain,Authentication authResult) throws IOException {
        Object obj = authResult.getPrincipal();
        AccountDetails user = (AccountDetails)obj;

        // 从User中获取权限信息
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        //创建Token
        String token = JwtTokenUtil.createToken(user.getUsername(), authorities.toString());
        //设置编码 防止乱码问题
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        //在请求头里返回创建成功的token
        //设置请求头为带有 “”前缀的token字符串
        response.setHeader("token", JwtTokenUtil.TOKEN_PREFIX + token);
        // 处理编码方式 防止中文乱码
        response.setContentType("text/json;charset=utf-8");
        // 将反馈塞到HttpServletResponse中返回给前台
        LoginUserVO loginUserVO = new LoginUserVO();
        loginUserVO.setLoginName(user.getUsername());
        loginUserVO.setNickName(user.getNickname());
        loginUserVO.setToken(token);
        ApiResponse apiResponse = ApiResponse.success(loginUserVO);
        response.getWriter().write(JSON.toJSONString(apiResponse));
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        String msg = "";
        //账号过期
        if(failed instanceof AccountExpiredException){
            msg = "账号已过期";
        }
        else if(failed instanceof BadCredentialsException){
            msg = "密码错误";
        }
        else if(failed instanceof CredentialsExpiredException){
            msg = "密码已过期";
        }// 账号不可用
        else if (failed instanceof DisabledException) {
            msg="账号不可用";
        }
        //账号锁定
        else if (failed instanceof LockedException) {
            msg="账号已锁定";
        }
        // 用户不存在
        else if (failed instanceof InternalAuthenticationServiceException) {
            msg="用户不存在";
        }
        // 其他错误
        else{
            msg="未知异常";
        }
        // 处理编码方式 防止中文乱码
        response.setContentType("text/json;charset=utf-8");
        // 将反馈塞到HttpServletResponse中返回给前台
        ApiResponse apiResponse = ApiResponse.fail(ApiResponseEnum.USER_DEFINED_ERROR.getCode(), msg);
        response.getWriter().write(JSON.toJSONString(apiResponse));
    }
}
