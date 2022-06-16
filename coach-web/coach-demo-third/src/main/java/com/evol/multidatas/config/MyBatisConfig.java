package com.evol.multidatas.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.annotation.Resource;

@Slf4j
@Configuration
@EnableTransactionManagement
//@ComponentScan("com.evol.multidatas.mapper")
@ConditionalOnProperty(name = {"spring.datasource.dynamic.enable"}, matchIfMissing = true, havingValue = "true")
@MapperScan("com.evol.multidatas.mapper")
public class MyBatisConfig {

    @Resource(name = "routingDataSource")
    private RoutingDataSource routingDataSource;

    /**
     * routingDataSource sqlSessionFactory
     * @return
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception{
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(routingDataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/**/*.xml"));
        sqlSessionFactoryBean.setTypeAliasesPackage("com.evol.multidatas.domain");
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory){
       return new SqlSessionTemplate(sqlSessionFactory);
    }

//    @Bean
//    public PlatformTransactionManager txManager() {
//        return new DataSourceTransactionManager(routingDataSource);
//    }
//
//
//    @Override
//    public PlatformTransactionManager annotationDrivenTransactionManager() {
//        return txManager();
//    }
}
