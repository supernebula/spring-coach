package com.evol.aop;

import com.evol.aop.annotation.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;

//定义切面，@Aspect使之成为切面类
@Aspect
@Component
public class AopAspect {

    //Pointcut是植入Advice的触发条件。每个Pointcut的定义包括2部分，一是表达式，二是方法签名。方法签名必须是 public及void型。可以将Pointcut中的方法看作是一个被Advice引用的助记符，因为表达式不直观，因此我们可以通过方法签名的方式为 此表达式命名。因此Pointcut中的方法只需要方法签名，而不需要在方法体内编写实际代码
    //定义切入点，切入点为标com.evol.buniness包及其子包的，通过@Pointcut生命切入点表达式
    //@Pointcut("execution(public * com.evol.buniness.*.*(..))")
    //定义切入点，切入点为标有注解@Log的所有函数，通过@Pointcut生命切入点表达式
    @Pointcut("@annotation(com.evol.aop.annotation.Log)")
    public void LogAspect(){
    }

    //在连接点执行之前执行的通知
    @Before("LogAspect()")
    public void doBeforeLog(){
        System.out.println("执行controller前置通知");
    }

    //使用环绕通知，注意该方法需要返回值
    @Around("LogAspect()")
    public Object doAroundLog(ProceedingJoinPoint pjp) throws  Throwable{
        try{
            System.out.println("开始执行contoller环绕通知");
            Object obj = pjp.proceed();
            System.out.println("结束执行controller环绕通知");
            return obj;
        }catch (Throwable e){
            System.out.println("出现异常");
            throw e;
        }
    }

    //在连接点执行结束之后执行的通知
    @After("LogAspect()")
    public void doAfterLog(){
        System.out.println("执行Controller后置结束通知");
    }

    //在连接点执行结束并返回之后执行的通知
    @AfterReturning("LogAspect()")
    public void doAfterReturnLog(JoinPoint joinPoint){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Log log = method.getAnnotation(Log.class);
        String name = log.name();
        System.out.println(name);
        System.out.println("执行controller后置返回通知");
    }

    @AfterThrowing(pointcut = "LogAspect()", throwing = "e")
    public void doAfterThrowingLog(JoinPoint joinPoint, Throwable e) {
        System.out.println("=======异步通知开始======");
        System.out.println("异步代码：" + e.getClass().getName());
        System.out.println("异步信息：" + e.getMessage());
    }
}


