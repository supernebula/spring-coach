package com.evol.controller;

import com.evol.constants.ColumnNames;
import com.evol.service.OAuthService;
import com.evol.utils.WeChatAuthorizeUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/WxOAuth")
public class WxOAuthController {

    @Autowired
    private OAuthService oAuthService; //授权service层接口


    @Autowired
    private WeChatAuthorizeUtil weChatAuthorizeUtil; //微信授权工具类， 后面有

    /**
     * 第一步：用户同意授权，获取code
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "授权登录", response = String.class)
    @GetMapping(value = "/getCode")
    public void loginInit(HttpServletResponse response) throws IOException {
        response.sendRedirect(weChatAuthorizeUtil.getOAuthCodeUrlForUser());
    }


    @GetMapping(value = "/getOAuth")
    public void getOAuth(HttpServletRequest request) {
        //此处直接获取请求的code，因为当用户点击授权后，会跳转到授权后重定向地址，也就是这个方法，从而携带过来code
        oAuthService.getWXUserInfoOAuth(request.getParameter(ColumnNames.CODE));
    }

//    @GetMapping("getCode")
//    @ResponseBody
//    public Object getCode(@RequestParam(name = "code", required = false) String code){}

}
