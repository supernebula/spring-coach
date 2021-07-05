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
@WebFilter(filterName = "AccessAuditFilter",urlPatterns = {"/*"})
public class AccessAuditFilter  implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest=(HttpServletRequest)servletRequest;
        HttpServletResponse httpServletResponse=(HttpServletResponse)servletResponse;

        String uri = httpServletRequest.getRequestURI();
        String httpMethod = httpServletRequest.getMethod();
        Long startTime = System.currentTimeMillis();
        filterChain.doFilter(servletRequest,servletResponse);
        Long endTime = System.currentTimeMillis();
        String queryString = httpServletRequest.getQueryString();
       log.info(String.format("%s %s, %s,   耗时毫秒数: %s", httpMethod,  uri, queryString, (endTime - startTime)));

    }
}
