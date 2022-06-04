package com.evol.sflannot;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "secondFilter", urlPatterns = "/**")
public class SecondFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("SecondFilter");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("SecondFilter init");
    }

    @Override
    public void destroy() {
        System.out.println("SecondFilter destroy");
    }
}
