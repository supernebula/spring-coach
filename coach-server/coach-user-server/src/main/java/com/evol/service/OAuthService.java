package com.evol.service;

import com.evol.domain.dto.WxTokenRespDTO;

public interface OAuthService {

    void getWXUserInfoOAuth(String code); //授权获取用户信息接口

    WxTokenRespDTO getToken(String code);

}
