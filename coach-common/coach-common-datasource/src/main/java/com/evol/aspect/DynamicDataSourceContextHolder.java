package com.evol.aspect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DynamicDataSourceContextHolder {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private static final ThreadLocal<String> currentDataSourceKey = new ThreadLocal<String>();

    public static void setDataSourceKey(String dataSourceKey){
        currentDataSourceKey.set(dataSourceKey);
    }

    public static String getDataSourceKey(){
        return currentDataSourceKey.get();
    }

    public static void clearDataSourceKey(){
        currentDataSourceKey.remove();
    }

}
