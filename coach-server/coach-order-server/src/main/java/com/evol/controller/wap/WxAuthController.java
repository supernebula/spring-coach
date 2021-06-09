package com.evol.controller.wap;

import com.evol.config.WXPayConfig;
import com.evol.domain.wechat.AccessTokenResult;
import com.evol.util.JsonUtil;
import com.evol.web.ApiResponse;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

@RestController
@RequestMapping("/wxAuth")
public class WxAuthController {


    @Autowired
    private WXPayConfig wXPayConfig;

    @GetMapping("reqCodeUri")
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

}
