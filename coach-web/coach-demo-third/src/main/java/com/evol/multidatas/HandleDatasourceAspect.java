package com.evol.multidatas;

import com.evol.multidatas.annotation.DataSource;
import com.evol.multidatas.config.DataSourceContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 切换数据源的切面
 * 可以定义多个切点，每个切点可以配置嵌入逻辑@Before（执行前）、 @After（执行后）
 */
@Aspect
@Component
public class HandleDatasourceAspect {



    //1. 定义切点（查询条件）1，这个切点有注解DataSource标记
    @Pointcut("@annotation(com.evol.multidatas.annotation.DataSource)")
    public void dsPointcut() {
    }

    @Before("dsPointcut()")
    public void beforeExecute(JoinPoint joinPoint){
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        DataSource annotation = method.getAnnotation(DataSource.class);
        if(annotation == null){
            annotation = joinPoint.getTarget().getClass().getAnnotation(DataSource.class);
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

    //1. 定义切点（查询条件）1，这个切点根据名称空间为查询条件
    @Pointcut("execution(public * com.evol.multidatas.service.user.*.*(..))")
    public void masterPointcut() {
    }

}
