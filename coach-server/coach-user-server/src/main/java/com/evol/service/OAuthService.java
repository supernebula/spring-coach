package com.evol.service;

import com.evol.domain.dto.WxOAuthTokenRespDTO;
public interface OAuthService {

    void getWXUserInfoOAuth(String code); //授权获取用户信息接口

    WxOAuthTokenRespDTO getToken(String code);

}
