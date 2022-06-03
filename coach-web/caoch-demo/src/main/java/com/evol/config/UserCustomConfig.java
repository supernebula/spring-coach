package com.evol.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:user.properties")
//@PropertySource(value={"classpath:xxx1.properties","classpath:xxx2.properties"})
@ConfigurationProperties(prefix = "user", ignoreInvalidFields = true) //读取配置文件resources/user.properties中key为user的属性
@Data
public class UserCustomConfig {

    private String realName;

    private Integer age;
}
