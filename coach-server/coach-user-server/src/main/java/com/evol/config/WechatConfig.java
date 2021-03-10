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

    /**
     * 微信公众号网页授权,第一步：用户同意授权，获取codeURL模板
     */
    public static final String OAUTH_GET_CODE_URI_TEMPLATE = "https://open.weixin.qq" +
            ".com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";


    /**
     * 第二步：通过code换取网页授权access_token URL模板
     */
    public static final String OAUTH_GET_ACCESS_TOKEN_URI_TEMPLATE = "https://api.weixin.qq" +
            ".com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";

    /**
     *第三步：刷新access_token（如果需要） URL模板
     */
    public static final String OAUTH_REFRESH_TOKEN_URI_TEMPLATE = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";


    /**
     * 第四步：拉取用户信息(需scope为 snsapi_userinfo)
     */
    public static final String OAUTH_USER_INFO_URI_TEMPLATE = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

}
