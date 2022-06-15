package com.evol.multidatas.config;

import lombok.extern.slf4j.Slf4j;

/**
 * 数据源切换方法
 */
@Slf4j
public class DataSourceContextHolder {

    //再一次请求线程中，通过线程局部变量ThreadLocal，解决线程安全问题
    private static ThreadLocal<String> dataSourceContext = new ThreadLocal<>();

    public static void switchDataSource(String dataSource){
        log.debug("switchDataSource:{}", dataSource);
        dataSourceContext.set(dataSource);
    }

    public static String getDataSource(){
        return dataSourceContext.get();
    }

    public static void clear(){
        dataSourceContext.remove();
    }
}
