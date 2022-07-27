package com.evol.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * // 启用批处理功能
 * @author admin
 */
//@Configuration
//@EnableBatchProcessing
public class HelloJobConfiguration {

    //注入创建任务的对象
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    //注入创建步骤的对象
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job helloJob() {
        return jobBuilderFactory.get("helloJob")
                .start(step1()).build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .tasklet((stepContribution, chunkContext) -> {
                    System.out.println(Thread.currentThread().getName() + "------" + "hello world");
                    // 返回执行完成状态
                    return RepeatStatus.FINISHED;
                }).build();
    }
}
