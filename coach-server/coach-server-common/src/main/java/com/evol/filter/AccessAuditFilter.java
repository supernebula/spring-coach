package com.evol.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author admin
 */
@Slf4j
@WebFilter(filterName = "filter1",urlPatterns = {"/hello/*"})
public class AccessAuditFilter  implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest=(HttpServletRequest)servletRequest;
        HttpServletResponse httpServletResponse=(HttpServletResponse)servletResponse;

        String uri = ((HttpServletRequest) servletRequest).getRequestURI();
        String httpMethod = ((HttpServletRequest) servletRequest).getMethod();
        Long startTime = System.currentTimeMillis();

        filterChain.doFilter(servletRequest,servletResponse);
        Long endTime = System.currentTimeMillis();

       // log.info();


    }
}
