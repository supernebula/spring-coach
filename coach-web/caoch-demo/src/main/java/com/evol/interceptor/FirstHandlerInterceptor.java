package com.evol.interceptor;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FirstHandlerInterceptor implements HandlerInterceptor {

    //在执行处理程序（action）之前调用
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //默认返回true，如果返回false后续拦截器都会失效，但当前拦截器的afterCompletion会继续执行完。
        System.out.println("FirstHandlerInterceptor 正在执行preHandle. 请求路径：" + request.getRequestURI());
        return true;
    }

    //在执行处理程序（action）之后，但在呈现视图之前调用
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        System.out.println("FirstHandlerInterceptor 正在执行postHandle. 请求路径：" + request.getRequestURI());
    }

    //在执行处理程序（action）之后，呈现试图之后回调
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        System.out.println("FirstHandlerInterceptor 正在执行afterCompletion. 请求路径：" + request.getRequestURI());
    }
}
