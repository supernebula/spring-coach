package com.evol.multi;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@Slf4j
@EnableTransactionManagement
@ConditionalOnProperty(name = {"spring.datasource.dynamic.enable"}, matchIfMissing = false, havingValue = "true")
@MapperScan("com.evol.mapper")
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

    /**
     * 注入 DataSourceTransactionManager 用于事务管理
     */
    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dynamicDataSource());
    }

    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean =
                new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(this.dynamicDataSource());
        return sqlSessionFactoryBean.getObject();
    }

}
