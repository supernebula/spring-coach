package com.evol.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import org.springframework.batch.item.ItemProcessor
import com.javainuse.step.Processor;
import com.javainuse.step.Reader;
import com.javainuse.step.Writer;

@Configuration
@EnableBatchProcessing
public class SimpleJobConfig {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;


    public Job importExcelJob(){

        return jobBuilderFactory.get("importJob")
                .incrementer(new RunIdIncrementer()).listener(listener())
                .flow(importExcelStep1()).end().build();

    }

    public Step importExcelStep1(){
        return stepBuilderFactory.get("importExcelStep1")
                .<String, String>chunk(1)
                .reader(new Reader()).processor(new Processor())
                .writer(new Writer()).build();
    }



    @Bean
    public JobExecutionListener listener() {
        return new JobCompletionListener();
    }
}
