//package com.evol.aspect;
//
//import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
//import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
//import com.github.pagehelper.PageInterceptor;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang.ArrayUtils;
//import org.apache.ibatis.plugin.Interceptor;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//import javax.sql.DataSource;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Properties;
//
//@Configuration
//@Slf4j
//@EnableTransactionManagement
//@ConditionalOnProperty(name = {"spring.datasource.dynamic.enable"}, matchIfMissing = true, havingValue = "true")
//@MapperScan("com.evol.mapper")
//public class DataSourceConfigurer {
//
//    @Bean
//    @ConfigurationProperties(prefix = "spring.datasource.druid.business")
//    public DataSource businessDataSource(){
//        return DruidDataSourceBuilder.create().build();
//    }
//
//    @Bean
//    @ConfigurationProperties(prefix = "spring.datasource.druid.order")
//    public DataSource orderDataSource(){
//        return DruidDataSourceBuilder.create().build();
//    }
//
//    @Bean
//    @ConfigurationProperties(prefix = "spring.datasource.druid.user")
//    public DataSource userDataSource(){
//        return DruidDataSourceBuilder.create().build();
//    }
//
//    @Bean
//    @Primary
//    public DataSource dataSource(){
//        DynamicRoutingDataSource dataSource = new DynamicRoutingDataSource();
//        Map<Object, Object> dataSourceMap = new HashMap<>();
//        dataSourceMap.put(DataSourceKeyEnum.DATA_SOURCE_KEY_BUSINESS.getKey(), this.businessDataSource());
//        dataSourceMap.put(DataSourceKeyEnum.DATA_SOURCE_KEY_ORDER.getKey(), this.orderDataSource());
//        dataSourceMap.put(DataSourceKeyEnum.DATA_SOURCE_KEY_USER.getKey(), this.userDataSource());
//        dataSource.setTargetDataSources(dataSourceMap);
//        dataSource.setDefaultTargetDataSource(this.businessDataSource());
//        return dataSource;
//    }
//
////    /**
////     * 注入 DataSourceTransactionManager 用于事务管理
////     */
////    @Bean
////    public DataSourceTransactionManager transactionManager() {
////        return new DataSourceTransactionManager(dynamicDataSource());
////    }
////
//
//    @Bean
//    public MybatisSqlSessionFactoryBean sqlSessionFactoryBean(@Qualifier("dataSource") DataSource dataSource) throws Exception {
//        MybatisSqlSessionFactoryBean sessionFactory = new MybatisSqlSessionFactoryBean();
//        // 配置数据源，此处配置为关键配置，如果没有将 dynamicDataSource作为数据源则不能实现切换
//        sessionFactory.setDataSource(dataSource);
//
//        // 扫描Model
//        sessionFactory.setTypeAliasesPackage("com.evol.domain");
//        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        // 扫描映射文件
//        sessionFactory.setMapperLocations(resolver.getResources("classpath*:mapper/**/*Mapper.xml"));
//
//        // 添加插件
//        Interceptor[] interceptors = getPlugins();
//        if (ArrayUtils.isNotEmpty(interceptors)) {
//            sessionFactory.setPlugins(interceptors);
//        }
//
//        return sessionFactory;
//    }
//
//    private Interceptor[] getPlugins() {
//        Interceptor[] plugins = new Interceptor[0];
//
//        // PageHelper分页插件
//        PageInterceptor pageInterceptor = new PageInterceptor();
//        Properties properties = new Properties();
//        properties.setProperty("helperDialect", "mysql");
//        properties.setProperty("reasonable", "true");
//        pageInterceptor.setProperties(properties);
//
//        plugins = (Interceptor[]) ArrayUtils.add(plugins, pageInterceptor);
//        return plugins;
//    }
//
////    @Bean
////    public SqlSessionFactory sqlSessionFactory() throws Exception {
////        SqlSessionFactoryBean sqlSessionFactoryBean =
////                new SqlSessionFactoryBean();
////        sqlSessionFactoryBean.setDataSource(this.dataSource());
////        return sqlSessionFactoryBean.getObject();
////    }
//
////    @Bean
////    public SqlSessionFactory sqlSessionFactory( DataSource dataSource) throws Exception {
////        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
////        bean.setDataSource(this.dataSource());
////        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mappers/second/**/*Mapper.xml"));
////        return bean.getObject();
////    }
//
//
//    @Bean // 将数据源纳入spring事物管理
//    public DataSourceTransactionManager transactionManager(@Qualifier("dataSource") DataSource dataSource){
//        return new DataSourceTransactionManager(dataSource);
//    }
//}
