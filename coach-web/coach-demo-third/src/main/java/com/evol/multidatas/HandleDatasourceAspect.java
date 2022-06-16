package com.evol.multidatas;

import com.evol.multidatas.annotation.DataSourcePoint;
import com.evol.multidatas.config.DataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 切换数据源的切面
 * 可以定义多个切点，每个切点可以配置嵌入逻辑@Before（执行前）、 @After（执行后）
 */
@Aspect
@Order(-1)
@Component
@Slf4j
public class HandleDatasourceAspect {



    //1. 定义切点（查询条件）1，这个切点有注解DataSource标记
    @Pointcut("@annotation(com.evol.multidatas.annotation.DataSourcePoint)")
    public void dsPointcut() {
        log.debug("dsPointcut");
    }

    @Before("dsPointcut()")
    public void beforeExecute(JoinPoint joinPoint){
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        DataSourcePoint annotation = method.getAnnotation(DataSourcePoint.class);
        if(annotation == null){
            annotation = joinPoint.getTarget().getClass().getAnnotation(DataSourcePoint.class);
        }

        if(annotation != null){
            //切换数据源
            DataSourceContextHolder.switchDataSource(annotation.name());
        }
    }

    @After("dsPointcut()")
    public void afterExecute(){
        DataSourceContextHolder.clear();
    }

    //2. 定义business切点（查询条件）1，这个切点根据名称空间为查询条件
    @Pointcut("execution(public * com.evol.multidatas.service.business.*(..))")
    public void businessPointcut() {
        log.debug("businessPointcut");
    }

    @Before("businessPointcut()")
    public void businessBeforeExecute(JoinPoint joinPoint){
        //切换数据源
        log.debug("HandleDatasourceAspect point: business");
        DataSourceContextHolder.switchDataSource("business");
    }

    @After("businessPointcut()")
    public void businessAfterExecute(){
        DataSourceContextHolder.clear();
    }




    //3. 定义order切点（查询条件）1，这个切点根据名称空间为查询条件
    @Pointcut("execution(public * com.evol.multidatas.service.order.*(..))")
    public void orderPointcut() {
        log.debug("orderPointcut");
    }

    @Before("orderPointcut()")
    public void orderBeforeExecute(JoinPoint joinPoint){
        //切换数据源
        log.debug("HandleDatasourceAspect point: order");
        DataSourceContextHolder.switchDataSource("order");
    }

    @After("orderPointcut()")
    public void orderAfterExecute(){
        DataSourceContextHolder.clear();
    }

    //4. 定义user切点（查询条件）1，这个切点根据名称空间为查询条件
    @Pointcut("execution(public * com.evol.multidatas.service.user.*(..))")
    public void userPointcut() {
        log.debug("userPointcut");
    }

    @Before("userPointcut()")
    public void userBeforeExecute(JoinPoint joinPoint){
        //切换数据源
        log.debug("HandleDatasourceAspect point: user");
        DataSourceContextHolder.switchDataSource("user");
    }

    @After("userPointcut()")
    public void userAfterExecute(){
        DataSourceContextHolder.clear();
    }

}
