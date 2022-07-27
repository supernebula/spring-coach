package com.evol.config;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.support.ApplicationContextFactory;
import org.springframework.batch.core.configuration.support.GenericApplicationContextFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@EnableBatchProcessing(modular = true)
public class BatchConfiguration {

    @Bean
    public ApplicationContextFactory firstJobContext() {
        return new GenericApplicationContextFactory(HelloJobConfiguration.class);
    }

}
