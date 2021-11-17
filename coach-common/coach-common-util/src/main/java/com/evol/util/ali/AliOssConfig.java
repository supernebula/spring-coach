//package com.evol.util.ali;
//
//
//import com.aliyun.oss.OSS;
//import com.aliyun.oss.OSSClientBuilder;
//import lombok.Getter;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//
//@Component
//@Getter
//public class AliOssConfig {
//
//    @Value("ali.oss.endpoint")
//    private String endpoint;
//
//    @Value("ali.oss.accessKey")
//    private String accessKeyId;
//
//    @Value("ali.oss.accessSecret")
//    private String accessKeySecret;
//
//
//    @Bean
//    public OSS ossClient(){
//        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
//        return ossClient;
//    }
//}
