package com.evol.controller;

import com.evol.config.WXPayConfig;
import com.evol.domain.model.User;
import com.evol.enums.ApiResponseEnum;
import com.evol.service.UserService;
import com.evol.util.JsonUtil;
import com.evol.utils.WeChatAuthorizeUtil;
import com.evol.web.ApiResponse;
import com.evol.wechat.AccessTokenResult;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/WxOAuth")
public class WxOAuthController {

    @Autowired
    private WeChatAuthorizeUtil weChatAuthorizeUtil; //微信授权工具类， 后面有

    private UserService userService;

    @Autowired
    private WXPayConfig wXPayConfig;

    @CrossOrigin
    @ApiOperation(value = "授权登录", response = String.class)
    @GetMapping(value = "/requestCode")
    public ApiResponse reqCodeUri() throws IOException {
        String redirectUri = URLEncoder.encode(wXPayConfig.getReqCodeCallbackUrl(), StandardCharsets.UTF_8.toString()) ;
        System.out.println("redirectUri:" + redirectUri);
        String uri = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + wXPayConfig.getAppId() +
                "&redirect_uri=" + redirectUri + "&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
        return ApiResponse.success(uri);
    }

    @CrossOrigin
    @GetMapping("/requestToken")
    @ResponseBody
    public ApiResponse requestToken(@RequestParam(name = "code", required = false) String code){
        log.info("wechat auth code:" + code);
        String reqTokenUri = wXPayConfig.getWxTokenUrl() + "?appid=" + wXPayConfig.getAppId() +
                "&secret=" + wXPayConfig.getAppSecret() +"&code=" + code + "&grant_type=authorization_code";

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(Duration.ofSeconds(10))//连接超时
                .writeTimeout(Duration.ofSeconds(5))//写超时，也就是请求超时
                .readTimeout(Duration.ofSeconds(5))//读取超时
                .callTimeout(Duration.ofSeconds(15))//调用超时，也是整个请求过程的超时
                .build();

        Request request = new Request.Builder().url(reqTokenUri).build();

        String tokenRespStr = null;
        try (Response response = okHttpClient.newCall(request).execute()) {
            log.info("response.isSuccessful()?" + response.isSuccessful());
            if(!response.isSuccessful()){
                String errInfo = "获取微信token失败：" + response.code() + "，" + tokenRespStr;
                log.error(errInfo);
                return ApiResponse.fail(ApiResponseEnum.USER_DEFINED_ERROR, errInfo);
            }
            okhttp3.ResponseBody body = (okhttp3.ResponseBody) response.body();
            tokenRespStr = body.string();
        } catch (IOException ex) {
            log.error(ex.getMessage(), ex);
        }
            log.info("tokenRespStr" + tokenRespStr);
            //                {
//                    "access_token":"ACCESS_TOKEN",
//                        "expires_in":7200,
//                        "refresh_token":"REFRESH_TOKEN",
//                        "openid":"OPENID",
//                        "scope":"SCOPE"
//                }
        AccessTokenResult tokenResult = JsonUtil.ParseObject(tokenRespStr, AccessTokenResult.class);
        //保存到数据库
        Date expiresDate = new Date(System.currentTimeMillis() + (tokenResult.getExpiresIn() * 1000));
        User user = userService.saveWechatToken(tokenResult.getOpenid(),
                tokenResult.getAccessToken(),
                expiresDate,
                tokenResult.getRefreshToken(),
                tokenResult.getScope()
            );
        if(user != null)
        {
            String errInfo = "微信OAuth授权信息保存：" + tokenRespStr;
            log.error(errInfo);
            return ApiResponse.fail(ApiResponseEnum.USER_DEFINED_ERROR, errInfo);
        }
        return ApiResponse.success(tokenResult);
    }
}
