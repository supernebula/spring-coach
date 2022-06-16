package com.evol.multidatas.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 参考：https://www.jianshu.com/p/b158476dd33c
 * AbstractRoutingDataSource -- Spring提供的轻量级数据源切换方式
 * 实现AbstractRoutingDataSource
 */
@Slf4j
public class RoutingDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        log.info("Current DataSource is :" + DataSourceContextHolder.getDataSource());
        return DataSourceContextHolder.getDataSource();
    }
}
