package com.evol.sflcode.config;

import com.evol.sflcode.FirstFilter;
import com.evol.sflcode.FirstListenter;
import com.evol.sflcode.FirstServlet;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import javax.servlet.ServletContextListener;
import java.util.ArrayList;
import java.util.List;

//3.7.1 使用编码方式注册S servlet、F filter、L listener
@Configuration
public class WebConfig {

    //使用代码注册Servlet （不使用@ServletComponentScan注解）
    @Bean
    public ServletRegistrationBean getFirstServlet(){
        ServletRegistrationBean servRegBean = new ServletRegistrationBean();
        servRegBean.setServlet(new FirstServlet());
//        List<String> urlMappings = new ArrayList<>();
//        urlMappings.add("/first");
        servRegBean.addUrlMappings("/first", "/firstServlet");  //访问Url,可以添加多个
        servRegBean.setLoadOnStartup(1); //设置加载顺序
        return servRegBean;
    }

    //注册过滤器
    @Bean
    public FilterRegistrationBean getFirstFilter(){
        FilterRegistrationBean filterRegBean = new FilterRegistrationBean();
        filterRegBean.setFilter(new FirstFilter());
        List<String> urlPatterns = new ArrayList<>();
        urlPatterns.add("/*"); //拦截路径，可以添加多个
        filterRegBean.setUrlPatterns(urlPatterns);
        filterRegBean.setOrder(1); //设置注册顺序
        return filterRegBean;
    }

    //注册监听器
    @Bean
    public ServletListenerRegistrationBean<ServletContextListener> getFirstListener(){
        ServletListenerRegistrationBean listenRegBean = new ServletListenerRegistrationBean();
        listenRegBean.setListener(new FirstListenter());
        listenRegBean.setOrder(1);
        return listenRegBean;
    }
}
