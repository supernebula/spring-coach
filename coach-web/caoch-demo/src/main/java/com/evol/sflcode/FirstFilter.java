package com.evol.sflcode;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;


public class FirstFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("FirstFilter");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("FirstFilter init");
    }

    @Override
    public void destroy() {
        System.out.println("FirstFilter destroy");
    }
}
