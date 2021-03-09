package com.evol.service.impl;

import com.alibaba.fastjson.JSON;
import com.evol.domain.common.RespResultEnum;
import com.evol.domain.dto.UserRegisterRespDTO;
import com.evol.domain.dto.WeiXinOAuthRespDTO;
import com.evol.domain.dto.WeiXinOAuthUserInfoRespDTO;
import com.evol.domain.entity.UserDO;
import com.evol.domain.enums.UserTypeEnum;
import com.evol.mapper.UserMapper;
import com.evol.service.OAuthService;
import com.evol.service.common.CommonTemplateService;
import com.evol.utils.JwtUtils;
import com.evol.utils.WeChatAuthorizeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

@Service
@Slf4j
public class OAuthServiceImpl implements OAuthService {

    @Autowired
    private WeChatAuthorizeUtil weChatAuthorizeUtil;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CommonTemplateService templateService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private HttpServletResponse response;

    @Override
    public void getWXUserInfoOAuth(String code) {
        try {
            if (StringUtils.isBlank(code)) {            //判断请求 code 是否为空
                log.error(" CODE_NOT_EMPTY_ERROR : << {} >>", RespResultEnum.CODE_NOT_EMPTY_ERROR.getMessage());
            }
            UserRegisterRespDTO registerRespDTO = null; //自己封装的，返回给前段的数据（包含jwt中加密的token，和用户的信息）
            ResponseEntity<String> responseEntity =    //使用resttemplate向微信提供的url（通过code换取网页授权access_token url）发送请求
                    templateService.getForEntity(weChatAuthorizeUtil.getAccessTokenUrl(code));
            if (responseEntity.getStatusCode().value() != HttpStatus.OK.value()) { //判断请求是否成功
                log.error(" VISIT_WEIXIN_API_ERROR : << {} >>", RespResultEnum.VISIT_WEIXIN_API_ERROR.getMessage()
                        + responseEntity.getStatusCodeValue());
            }
            WeiXinOAuthRespDTO weiXinOAuthRespDTO =        //获取微信“通过code换取网页授权access_token”返回的信息，并封装到自己的DTO中
                    JSON.parseObject(responseEntity.getBody(), WeiXinOAuthRespDTO.class);
            log.info("weiXinOAuthRespDTO ： " + weiXinOAuthRespDTO.toString()); //日志打印
            if (StringUtils.isBlank(weiXinOAuthRespDTO.getOpenid())) { //判断返回的openID是否为空
                log.error(" WEIXIN_CODE_ERROR : << {} >>", RespResultEnum.WEIXIN_CODE_ERROR.getMessage());
            }
            ResponseEntity<String> responseUserInfo = //使用resttemplate向微信提供的url（拉取用户信息 url）发送请求
                    templateService.getForEntity(weChatAuthorizeUtil.getUserInfoUrl(weiXinOAuthRespDTO.
                            getAccess_token(), weiXinOAuthRespDTO.getOpenid()));
            if (responseEntity.getStatusCode().value() != HttpStatus.OK.value()) { //判断请求是否成功
                log.error(" VISIT_WEIXIN_API_ERROR : << {} >>", RespResultEnum.VISIT_WEIXIN_API_ERROR.getMessage()
                        + responseEntity.getStatusCodeValue());
            }
            WeiXinOAuthUserInfoRespDTO userInfoRespDTO =    //获取微信“拉取用户信息”返回的信息，并封装到自己的DTO中(此dto包含用户头像，昵称等)
                    JSON.parseObject(responseUserInfo.getBody(), WeiXinOAuthUserInfoRespDTO.class);
            if (StringUtils.isBlank(userInfoRespDTO.getOpenid())) {
                log.error(" WEIXIN_USER_INFO_ERROR : << {} >>", RespResultEnum.WEIXIN_USER_INFO_ERROR.getMessage());
            }
            log.info("weiXinOAuth user info： " + userInfoRespDTO.toString());
            UserDO userDO = getUserByOpenId(userInfoRespDTO.getOpenid()); //此方法在下面
            if (userDO != null) { // == null 说明 该用户第一次授权（此时：我们需要做两点==> 1、把用户信息保存到数据库；2、给前段签发token）
                //用户数据保存 数据库（省略。。。）
                //下面通过jwt向前段签发token（两个参数：1、userId,2、用户角色）
                String token = jwtUtils.generateJwt(userDO.getUserId(),
                        String.valueOf(UserTypeEnum.WEI_XIN_USER.getUserType()));
                registerRespDTO = respDTO(userDO, token);
                // 由于项目含有不在公众号中的登录方式：因此，做了一下的从定向，（如果你项目自有公众号，到上面那一步就结束了，也就是说直接返回 registerRespDTO 就行，其中包含用户信息，以及token）
                response.sendRedirect("自己想要跳转的地方");
                return;
            }
            //此处说明用户 不是第一次授权，数据库已经有用户信息，直接签发token跳转页面（若自有工作号，直接返回，不用从定向）
            response.sendRedirect("自己想要跳转的地方");
            return;
        } catch (Exception e) {
            log.error(" getWXUserInfoOAuth error: " + e.getMessage());
        }
    }

    private UserDO getUserByOpenId(String openId) {
        return null;
//        QueryWrapper<UserDO> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq(ColumnNames.OPEN_ID, openId);
//        return userMapper.selectOne(queryWrapper);
    }

    private UserRegisterRespDTO respDTO(UserDO user, String token) {
        UserRegisterRespDTO respDTO = new UserRegisterRespDTO();
        respDTO.setToken(token);
        respDTO.setUserId(String.valueOf(user.getUserId()));
        respDTO.setUserName(user.getUserName());
        respDTO.setNickName(user.getNickName());
        respDTO.setOpenid(user.getOpenid());
        respDTO.setUserImg(user.getUserImg());
        respDTO.setMobile(user.getMobile());
        respDTO.setSex(String.valueOf(user.getSex()));
        respDTO.setProvince(user.getProvince());
        respDTO.setCity(user.getCity());
        respDTO.setCounty(user.getCounty());
        respDTO.setCreateTime(String.valueOf(user.getCreateTime()));
        respDTO.setUpdateTime(String.valueOf(user.getUpdateTime()));
        return respDTO;
    }
}
