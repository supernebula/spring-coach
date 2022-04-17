package com.evol.util;

import com.evol.domain.dto.CurrentLoginAccount;

/**
 * 当前线程工具类
 */
public class ThreadLocalUtil {

    /**
     * 保存用户的ThreadLocal，在拦截器添加、删除相关用户数据
     */
    private static final ThreadLocal<CurrentLoginAccount> accountThreadLocal = new ThreadLocal<CurrentLoginAccount>();

    /**
     * 添加当前登录用户方法  在拦截器方法执行前调用设置获取用户
     * @param account
     */
    public static void addCurrentAccount(CurrentLoginAccount account){
        accountThreadLocal.set(account);
    }

    /**
     * 获取当前登录用户方法
     * @return
     */
    public static CurrentLoginAccount getCurrentAccount(){
        return accountThreadLocal.get();
    }

    /**
     * 删除当前登录用户方法  在拦截器方法执行后 移除当前用户对象
     */
    public static void remove(){
        accountThreadLocal.remove();
    }
}
