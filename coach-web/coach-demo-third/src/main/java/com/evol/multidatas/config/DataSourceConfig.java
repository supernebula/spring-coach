package com.evol.multidatas.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 注入数据源
 * @author admin
 */
@Configuration
public class DataSourceConfig {

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.druid.user")
    public DataSource userDataSource(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.druid.order")
    public DataSource orderDataSource(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.druid.business")
    public DataSource businessDataSource(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "routingDataSource")
    public RoutingDataSource routingDataSource(){
        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put("user", userDataSource());
        dataSourceMap.put("order", orderDataSource());
        dataSourceMap.put("business", businessDataSource());

        RoutingDataSource routingDataSource = new RoutingDataSource();
        routingDataSource.setTargetDataSources(dataSourceMap);
        routingDataSource.setDefaultTargetDataSource(userDataSource());
        return routingDataSource;
    }
}
