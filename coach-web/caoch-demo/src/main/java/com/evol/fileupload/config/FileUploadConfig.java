package com.evol.fileupload.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import java.io.IOException;

/**
 * 如果需要，更换为apache Commons FileUpload包
 */
@Configuration
public class FileUploadConfig{
    @Bean
    public MultipartResolver multipartResolver() throws IOException{
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        return multipartResolver;
    }
}