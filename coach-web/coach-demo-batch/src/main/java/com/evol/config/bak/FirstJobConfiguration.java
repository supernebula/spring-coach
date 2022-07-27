//package com.evol.config;
//
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.job.builder.JobBuilder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//
//public class FirstJobConfiguration {
//
//    @Autowired
//    private JobBuilderFactory jobBuilderFactory;
//
//    public Job firstJob(@Qualifier("firstStep") Step firstStep){
//        return jobBuilderFactory.get("firstJob")
//                .start(firstStep)
//                .build();
//    }
//
//    @Autowired
//    private StepBuilderFactory stepBuilderFactory;
//
//    public Step firstStep(){return null;};
//
//
//}
