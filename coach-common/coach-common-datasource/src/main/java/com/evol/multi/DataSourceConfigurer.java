package com.evol.multi;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import net.sf.jsqlparser.expression.operators.relational.OldOracleJoinBinaryExpression;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class DataSourceConfigurer {


    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.druid.business")
    public DataSource businessDataSource(){
        return DruidDataSourceBuilder.create().build();

    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.druid.order")
    public DataSource orderDataSource(){
        return DruidDataSourceBuilder.create().build();

    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.druid.user")
    public DataSource userDataSource(){
        return DruidDataSourceBuilder.create().build();

    }

    @Bean
    @Primary
    public DataSource dynamicDataSource(){
        DynamicRoutingDataSource dataSource = new DynamicRoutingDataSource();
        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put(DataSourceKeyEnum.DATA_SOURCE_KEY_BUSINESS, this.businessDataSource());
        dataSourceMap.put(DataSourceKeyEnum.DATA_SOURCE_KEY_ORDER, this.orderDataSource());
        dataSourceMap.put(DataSourceKeyEnum.DATA_SOURCE_KEY_USER, this.userDataSource());
        dataSourceMap.put(DataSourceKeyEnum.DATA_SOURCE_KEY_BUSINESS, this.businessDataSource());
        dataSource.setTargetDataSources(dataSourceMap);
        dataSource.setDefaultTargetDataSource(this.businessDataSource());
        return dataSource;
    }
}
