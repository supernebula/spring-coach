package com.evol.service.impl;

import com.alibaba.fastjson.JSON;
import com.evol.domain.dto.WxOAuthTokenRespDTO;
import com.evol.domain.dto.WxOAuthUserInfoRespDTO;
import com.evol.domain.model.User;
import com.evol.domain.model.UserExample;
import com.evol.mapper.UserMapper;
import com.evol.service.WxOauthService;
import com.evol.utils.HttpClientUtil;
import com.evol.utils.WeChatAuthorizeUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class WxOauthServiceImpl implements WxOauthService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private WeChatAuthorizeUtil weChatAuthorizeUtil;

    @Override
    public WxOAuthTokenRespDTO getToken(String code) {

        String tokenUri = weChatAuthorizeUtil.getAccessTokenUrl(code);

        String resultJsonStr = HttpClientUtil.doGet(tokenUri);
        WxOAuthTokenRespDTO wxTokenRespDTO = JSON.parseObject(resultJsonStr, WxOAuthTokenRespDTO.class);

        Date expiresIn = new Date(((new Date()).getTime()) + (wxTokenRespDTO.getExpiresIn() * 1000));
        User user = new User();
        user.setUsername("");
        user.setPassword("");
        user.setSalt("");
        user.setNickname("");
        user.setOpenId(wxTokenRespDTO.getOpenId());
        user.setAccessToken(wxTokenRespDTO.getAccessToken());
        user.setTokenExpiresIn(expiresIn);
        user.setRefreshToken(wxTokenRespDTO.getRefresToken());
        user.setScope(wxTokenRespDTO.getScope());
        user.setUserImg("");
        user.setMobile("");
        user.setGender(0);
        user.setProvince("");
        user.setCity("");
        user.setCounty("");
        userMapper.insertSelective(user);
        return wxTokenRespDTO;
    }

    @Override
    public WxOAuthTokenRespDTO refreshToken(String refreshToken){
        String tokenUri = weChatAuthorizeUtil.getRefreshTokenUrl(refreshToken);
        String resultJsonStr = HttpClientUtil.doGet(tokenUri);
        WxOAuthTokenRespDTO wxTokenRespDTO = JSON.parseObject(resultJsonStr, WxOAuthTokenRespDTO.class);

        UserExample userExample = new UserExample();
        userExample.createCriteria().andOpenIdEqualTo(wxTokenRespDTO.getOpenId());
        List<User> userList = userMapper.selectByExample(userExample);
        if(userList.size() < 1){ return wxTokenRespDTO;}
        User user = userList.get(0);

        Date expiresIn = new Date(((new Date()).getTime()) + (wxTokenRespDTO.getExpiresIn() * 1000));
        user.setAccessToken(wxTokenRespDTO.getAccessToken());
        user.setTokenExpiresIn(expiresIn);
        user.setRefreshToken(wxTokenRespDTO.getRefresToken());
        user.setScope(wxTokenRespDTO.getScope());
        userMapper.updateByPrimaryKeySelective(user);
        return wxTokenRespDTO;
    }

    @Override
    public WxOAuthUserInfoRespDTO getWxUserInfoByToken(String accessToken, String openId){
        String userInfoUri = weChatAuthorizeUtil.getUserInfoUrl(accessToken, openId);
        String resultJsonStr = HttpClientUtil.doGet(userInfoUri);
        WxOAuthUserInfoRespDTO wxUserDTO = JSON.parseObject(resultJsonStr, WxOAuthUserInfoRespDTO.class);

        UserExample userExample = new UserExample();
        userExample.createCriteria().andOpenIdEqualTo(wxUserDTO.getOpenId());
        List<User> userList = userMapper.selectByExample(userExample);
        if(userList.size() < 1){ return wxUserDTO;}
        User user = userList.get(0);
        user.setNickname(wxUserDTO.getNickname());
        user.setGender(wxUserDTO.getSex());
        user.setProvince(wxUserDTO.getProvince());
        user.setCity(wxUserDTO.getCity());
        user.setCounty(wxUserDTO.getCountry());
        user.setUserImg(wxUserDTO.getHeadImgUrl());
        userMapper.updateByPrimaryKeySelective(user);
        return wxUserDTO;
    }
}
