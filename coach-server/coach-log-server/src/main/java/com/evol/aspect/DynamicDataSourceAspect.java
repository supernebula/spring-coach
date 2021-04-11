package com.evol.aspect;


import com.evol.aspect.DataSourceKeyEnum;
import com.evol.aspect.DynamicDataSourceContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(-1)
@Component
public class DynamicDataSourceAspect {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Pointcut("execution(public * com.evol.service.business.*.*(..))")
    public void buninessCut(){}

    @Pointcut("execution(public * com.evol.service.order.*.*(..))")
    public void orderCut(){}

    @Pointcut("execution(public * com.evol.service.user.*.*(..))")
    public void userCut(){}

    @Before("buninessCut()")
    public void switchBuninessDataSource(JoinPoint point){
        DynamicDataSourceContextHolder.setDataSourceKey(DataSourceKeyEnum.DATA_SOURCE_KEY_BUSINESS.getKey());
    }

    @After("buninessCut()")
    public void restoreBuninessDataSource(){

        DynamicDataSourceContextHolder.clearDataSourceKey();
    }

    @Before("orderCut()")
    public void switchOrderDataSource(JoinPoint point){
        DynamicDataSourceContextHolder.setDataSourceKey(DataSourceKeyEnum.DATA_SOURCE_KEY_ORDER.getKey());
    }

    @After("orderCut()")
    public void restoreOrderDataSource(){
        DynamicDataSourceContextHolder.clearDataSourceKey();
    }

    @Before("userCut()")
    public void switchUserDataSource(JoinPoint point){
        DynamicDataSourceContextHolder.setDataSourceKey(DataSourceKeyEnum.DATA_SOURCE_KEY_USER.getKey());
    }

    @After("userCut()")
    public void restoreUserDataSource(){

        DynamicDataSourceContextHolder.clearDataSourceKey();
    }
}
