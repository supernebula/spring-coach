package com.evol.filter;

import com.evol.domain.OpenApiResponse;
import com.evol.domain.OpenApiResponseEnum;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Service
public class OauthAccessAspect {

    private static final Logger logger = LoggerFactory.getLogger(OauthAccessAspect.class);

    /**
     * 定义拦截规则：拦截所有@OauthAccess注解的方法。
     */
    @Pointcut("@annotation(OauthAccess)")
    public void oauthAccessPointCut(){}

    @Around("oauthAccessPointCut()&&@annotation(oauthAccess)")
    public Object Interceptor(ProceedingJoinPoint joinPoint, OauthAccess oauthAccess) throws Throwable {
        logger.debug("******拦截前的逻辑******");
        logger.debug("目标方法名为:" + joinPoint.getSignature().getName());
        logger.debug("目标方法所属类的简单类名:" + joinPoint.getSignature().getDeclaringType().getSimpleName());
        logger.debug("目标方法所属类的类名:" + joinPoint.getSignature().getDeclaringTypeName());
        //获取传入目标方法的参数
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            System.out.println("第" + (i + 1) + "个参数为:" + args[i]);
        }

        System.out.println("拦截的注解的参数：");
        System.out.println(oauthAccess.sign());
        System.out.println(oauthAccess.token());

        boolean sign = oauthAccess.sign();
        boolean token = oauthAccess.token();


        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        logger.debug("请求url===================" + request.getRequestURI());
        logger.debug("doFilter, querystring : "+ request.getQueryString());

        if(!sign){
            return OpenApiResponse.fail(OpenApiResponseEnum.SIGN_ERROR);
        }

        if(!token){
            return OpenApiResponse.fail(OpenApiResponseEnum.TOKEN_ERROR);
        }



        return joinPoint.proceed();



    }

}
