package com.evol.controller;

import com.evol.constants.ColumnNames;
import com.evol.service.OAuthService;
import com.evol.utils.WeChatAuthorizeUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

@Slf4j
@RestController
@RequestMapping("/OAuth")
public class WxOAuthController {

    @Autowired
    private OAuthService oAuthService; //授权service层接口

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private WeChatAuthorizeUtil weChatAuthorizeUtil; //微信授权工具类， 后面有

    /**
     *  第一步：前段调用此方法，当用户点击同意授权后，
     * 		  小编直接让他跳转调下面那个方法（/getOAuth）来获取网页授权access_token
     *	★★★：redirect_uri 授权后重定向的回调链接地址
     *			这个值填写的是下面 那个方法的url
     */
    @ApiOperation(value = "授权登录", response = String.class)
    @GetMapping(value = "/getCode")
    public void loginInit() {
        try {
            response.sendRedirect(weChatAuthorizeUtil.getOAuthCodeUrl_USER());
        } catch (UnsupportedEncodingException e) {
            log.error("重定向url编码失败：>> " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            log.error(" response重定向失败：>> " + e.getMessage());
        }
    }

    @GetMapping(value = "/getOAuth")
    public void getOAuth() {
        try {		//此处直接获取请求的code，因为当用户点击授权后，会跳转到授权后重定向地址，也就是这个方法，从而携带过来code
            oAuthService.getWXUserInfoOAuth(request.getParameter(ColumnNames.CODE));
        } catch (Exception e) {
            log.error("getOAuth error: ", e);
        }
    }

}
