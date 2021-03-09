package com.evol.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("wechat")
public class WechatConfig {

    /**
     * 公众号APPid
     */
    private String appId;

    /**
     *  公众号secretKey
     */
    private String secretKey;

    /**
     *  跳转地址
     */
    private String firstCodeRedirectUrl;

    /**
     *  跳转地址
     */
    private String loginCodeRedirectUrl;

}
