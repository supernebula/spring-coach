package com.evol.controller;

import com.evol.config.WXPayConfig;
import com.evol.constants.ColumnNames;
import com.evol.service.OAuthService;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

@Slf4j
@RestController
@RequestMapping("/WxOAuth")
@CrossOrigin
public class WxOAuthController {

    //@Autowired
    //private OAuthService oAuthService; //授权service层接口


    @Autowired
    private WeChatAuthorizeUtil weChatAuthorizeUtil; //微信授权工具类， 后面有


    @Autowired
    private WXPayConfig wXPayConfig;

    @ApiOperation(value = "授权登录", response = String.class)
    @GetMapping(value = "/requestCode")
    public ApiResponse reqCodeUri(HttpServletResponse response) throws IOException {
        String redirectUri = URLEncoder.encode(wXPayConfig.getAuthCodeUrl(), StandardCharsets.UTF_8.toString()) ;
        String uri = wXPayConfig.getAuthCodeUrl() + "?appid=" + wXPayConfig.getAppId() +
                "&redirect_uri=" + redirectUri + "&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
        return ApiResponse.success(uri);
    }

    @GetMapping("getToken")
    @ResponseBody
    public Object getToken(@RequestParam(name = "code", required = false) String code){
        System.out.println("code:" + code);


        String uri = wXPayConfig.getWxTokenUrl() + "?appid=" + wXPayConfig.getAppId() +
                "&secret=" + wXPayConfig.getAppSecret() +"&code=" + code + "&grant_type=authorization_code";

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(Duration.ofSeconds(10))//连接超时
                .writeTimeout(Duration.ofSeconds(5))//写超时，也就是请求超时
                .readTimeout(Duration.ofSeconds(5))//读取超时
                .callTimeout(Duration.ofSeconds(15))//调用超时，也是整个请求过程的超时
                .build();

        String bodyStr = "null";

        Request request = new Request.Builder().url(uri).build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            okhttp3.ResponseBody body = (okhttp3.ResponseBody) response.body();
            if (response.isSuccessful()) {
                bodyStr = body.string();
//                {
//                    "access_token":"ACCESS_TOKEN",
//                        "expires_in":7200,
//                        "refresh_token":"REFRESH_TOKEN",
//                        "openid":"OPENID",
//                        "scope":"SCOPE"
//                }

                AccessTokenResult tokenResult = JsonUtil.ParseObject(bodyStr, AccessTokenResult.class);

                //todo
                //保存到数据库


                System.out.println("success:" + body == null ? "" : bodyStr);
            } else {
                System.out.println("error,statusCode={" + response.code() + "},body={" + body == null ? "" :
                        body.string() + "}");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("bodyStr:" + bodyStr);

        return bodyStr;
    }


//    /**
//     * 第一步：用户同意授权，去请求获取code
//     * @param response
//     * @throws IOException
//     */
//    @ApiOperation(value = "授权登录", response = String.class)
//    @GetMapping(value = "/requestCode")
//    public void requestCode(HttpServletResponse response) throws IOException {
//        response.sendRedirect(weChatAuthorizeUtil.getOAuthCodeUrlForUser());
//    }
//
//
//    @GetMapping(value = "/getCode")
//    public void getOAuth(@RequestParam(name = "code", required = false) String code) {
//        //此处直接获取请求的code，因为当用户点击授权后，会跳转到授权后重定向地址，也就是这个方法，从而携带过来code
//        //oAuthService.getWXUserInfoOAuth(code);
//    }



}
