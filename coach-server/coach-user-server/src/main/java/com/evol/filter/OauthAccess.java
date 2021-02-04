package com.evol.filter;

import java.lang.annotation.*;

/**
 * @author
 * @Date 2021-02-03
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface OauthAccess {

    /**
     * 验证签名
     * @return
     */
    boolean sign() default true;

    /**
     * 验证Token
     * @return
     */
    boolean token() default true;

}

