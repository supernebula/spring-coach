//package com.evol.config;
//
//import com.evol.domain.model.Person;
//import com.evol.process.PersonItemProcessor;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.configuration.support.ApplicationContextFactory;
//import org.springframework.batch.core.configuration.support.GenericApplicationContextFactory;
//import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
//import org.springframework.batch.item.database.JdbcBatchItemWriter;
//import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
//import org.springframework.batch.item.file.FlatFileItemReader;
//import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
//import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ClassPathResource;
//
//import javax.sql.DataSource;
//
//@Configuration
//@EnableAutoConfiguration
//@EnableBatchProcessing(modular = true)
//public class BatchConfiguration {
//
//    @Bean
//    public ApplicationContextFactory firstJobContext() {
//        return new GenericApplicationContextFactory(FirstJobConfiguration.class);
//    }
//
//    @Bean
//    public ApplicationContextFactory secondJobContext() {
//        return new GenericApplicationContextFactory(SecondJobConfiguration.class);
//    }
//}
