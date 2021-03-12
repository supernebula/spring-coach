package com.evol.utils;

import com.evol.config.WechatConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Slf4j
@Component
public class WeChatAuthorizeUtil {

    private final static String ENCODE = "UTF-8";

    @Autowired
    private WechatConfig wechatConfig;

    // 微信授权 用户无感知 url（也就是用户不用点授权也能获取openID（不包含昵称、地区等），前提：在公众号内）
    public String getOAuthCodeUrl_BASE() {
        try {
            return WechatConfig.OAUTH_GET_CODE_URI_TEMPLATE
                    .replace("APPID", wechatConfig.getAppId())
                    .replace("REDIRECT_URI", URLEncoder.encode(wechatConfig.getFirstCodeRedirectUrl(), ENCODE))
                    .replace("SCOPE", "snsapi_base")
                    .replace("STATE", "CodeCow");
        }catch (UnsupportedEncodingException unEx){
            log.error(unEx.getMessage());
            return UnsupportedEncodingException.class.getSimpleName();
        }
    }

    /**
     * 微信公众号网页授权,第一步：用户同意授权，获取codeURL
     * @return
     */
    public String getOAuthCodeUrlForUser() {
        try {
            return WechatConfig.OAUTH_GET_CODE_URI_TEMPLATE
                    .replace("APPID", wechatConfig.getAppId())
                    .replace("REDIRECT_URI", URLEncoder.encode(wechatConfig.getLoginCodeRedirectUrl(), ENCODE))
                    .replace("SCOPE", "snsapi_userinfo")
                    .replace("STATE", "CodeCow");
        }catch (UnsupportedEncodingException unEx){
            log.error(unEx.getMessage());
            return UnsupportedEncodingException.class.getSimpleName();
        }
    }

    /**
     * 第二步：通过code换取网页授权access_token URL
     * @param code
     * @return
     */
    public String getAccessTokenUrl(String code) {
        return WechatConfig.OAUTH_GET_ACCESS_TOKEN_URI_TEMPLATE
                .replace("APPID", wechatConfig.getAppId())
                .replace("SECRET", wechatConfig.getSecretKey())
                .replace("CODE", code);
    }

    /**
     * 第三步：刷新access_token（如果需要） URL
     * @param refreshToken
     * @return
     */
    public String getRefreshTokenUrl(String refreshToken) {
        return WechatConfig.OAUTH_REFRESH_TOKEN_URI_TEMPLATE
                .replace("APPID", wechatConfig.getAppId())
                .replace("REFRESH_TOKEN", refreshToken);
    }

    /**
     * 第四步：拉取用户信息(需scope为 snsapi_userinfo)
     * @param accessToken
     * @param openid
     * @return
     */
    public String getUserInfoUrl(String accessToken, String openid) {
        return WechatConfig.OAUTH_USER_INFO_URI_TEMPLATE
                .replace("ACCESS_TOKEN", accessToken)
                .replace("OPENID", openid);
    }
}