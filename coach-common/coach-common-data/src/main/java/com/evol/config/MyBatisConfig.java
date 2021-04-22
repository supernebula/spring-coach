//package com.evol.config;
//
//import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
//import com.baomidou.mybatisplus.annotation.IdType;
//import com.baomidou.mybatisplus.core.MybatisConfiguration;
//import com.baomidou.mybatisplus.core.config.GlobalConfig;
//import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
//import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
//import com.github.pagehelper.PageHelper;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.ibatis.plugin.Interceptor;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.annotation.MapperScan;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.core.io.support.ResourcePatternResolver;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//import org.springframework.transaction.annotation.TransactionManagementConfigurer;
//
//import javax.sql.DataSource;
//import java.util.Properties;
//
//@Slf4j
//@Configuration
//@EnableTransactionManagement
//@ConditionalOnProperty(name = {"spring.datasource.one.enable"}, matchIfMissing = false, havingValue = "true")
//@MapperScan("com.evol.mapper")
//public class MyBatisConfig implements TransactionManagementConfigurer {
//
//
//    @Bean
//    @ConfigurationProperties(prefix = "spring.datasource.druid")
//    public DataSource dataSource(){
//        return DruidDataSourceBuilder.create().build();
//    }
//
//    @Bean
//    public SqlSessionFactory sqlSessionFactory() throws Exception {
//        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
//        sqlSessionFactory.setDataSource(dataSource());
//        sqlSessionFactory.setTypeAliasesPackage("com.evol.domain");
//        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        sqlSessionFactory.setMapperLocations(resolver.getResources("classpath:*/mapper/*.xml"));
//        //分页插件
//        PageHelper pageHelper = new PageHelper();
//        Properties properties = new Properties();
//        //设置为true时，会将RowBounds第一个参数offset当成pageNum页码使用
//        //和startPage中的pageNum效果一样
//        properties.setProperty("offsetAsPageNum", "true");
//        //设置为true时，使用RowBounds分页会进行count查询
//        properties.setProperty("rowBoundsWithCount", "true");
//        //启用合理化时，如果pageNum<1会查询第一页，如果pageNum>pages会查询最后一页
//        properties.setProperty("reasonable", "false");
//        //支持通过Mapper接口参数来传递分页参数
//        properties.setProperty("supportMethodsArguments", "true");
//        //always总是返回PageInfo类型,check检查返回类型是否为PageInfo,none返回Page
//        properties.setProperty("returnPageInfo", "check");
//        //参数映射:增加了一个`params`参数来配置参数映射，用于从Map或ServletRequest中取值
//        // properties.setProperty("params", "pageNum=page;pageSize=rows;orderBy=orderBy");
//        pageHelper.setProperties(properties);
//        //添加插件
//        sqlSessionFactory.setPlugins(new Interceptor[]{pageHelper});
//
//        MybatisConfiguration configuration = new MybatisConfiguration();
//        // configuration.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
//        // configuration.setLogImpl(org.apache.ibatis.logging.stdout.StdOutImpl.class);
//        configuration.setMapUnderscoreToCamelCase(true);
//        configuration.setCacheEnabled(false);
//        configuration.addInterceptor(new PaginationInterceptor());
//        sqlSessionFactory.setConfiguration(configuration);
//        GlobalConfig globalConfig = new GlobalConfig();
//        GlobalConfig.DbConfig dbConfig = new GlobalConfig.DbConfig();
//        dbConfig.setIdType(IdType.AUTO);
//        globalConfig.setDbConfig(dbConfig);
//        sqlSessionFactory.setGlobalConfig(globalConfig);
//        return sqlSessionFactory.getObject();
//
//    }
//
//    @Bean
//    public PlatformTransactionManager txManager() {
//        return new DataSourceTransactionManager(dataSource());
//    }
//
//    @Override
//    public PlatformTransactionManager annotationDrivenTransactionManager() {
//        return txManager();
//    }
//}
