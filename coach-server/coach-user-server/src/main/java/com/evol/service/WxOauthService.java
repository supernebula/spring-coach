package com.evol.service;

import com.evol.domain.dto.WxOAuthTokenRespDTO;
import com.evol.domain.dto.WxOAuthUserInfoRespDTO;

public interface WxOauthService {

    WxOAuthTokenRespDTO getToken(String code);

    WxOAuthTokenRespDTO refreshToken(String refreshToken);

    WxOAuthUserInfoRespDTO getWxUserInfoByToken(String accessToken, String openId);
}
