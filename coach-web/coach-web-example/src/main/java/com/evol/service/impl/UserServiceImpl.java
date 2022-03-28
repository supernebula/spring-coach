package com.evol.service.impl;

import com.evol.domain.dto.StaffDetails;
import com.evol.domain.model.Role;
import com.evol.domain.model.User;
import com.evol.domain.model.UserExample;
import com.evol.domain.model.UserRole;
import com.evol.mapper.UserMapper;
import com.evol.service.UserRoleService;
import com.evol.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleService userRoleService;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(s);
        List<User> list = userMapper.selectByExample(userExample);
        if(list == null || list.get(0) == null){
            return null;
        }

        User user = list.get(0);
        UserDetails userDetails = new StaffDetails(user.getUsername(), user.getPassword(), new ArrayList<Role>());
        return userDetails;
    }

    @Override
    public User addUser(String username, String password){
        if(StringUtils.isBlank(username) || StringUtils.isBlank(password)){
            return null;
        }

        //BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String pwd1 = passwordEncoder.encode(StringUtils.trim(password));
        log.info("pwd1:" + pwd1);

        User user = new User();
        user.setUsername(StringUtils.trim(username));
        user.setNickname(StringUtils.trim(username));
        user.setPassword(pwd1);
        user.setEmail("");
        user.setPhone("");
        user.setState(0);
        user.setSex(0);
        user.setCreateTime(new Date());
        userMapper.insert(user);
        return user;
    }

    public boolean addUser(User user, Integer[] roleIds) {
        int count = this.userMapper.insert(user);
        log.warn("更新用户角色为空");
        if (null != roleIds) {
            Arrays.stream(roleIds).forEach(roleId -> {
                UserRole ur = new UserRole();
                ur.setUserId(user.getId());
                ur.setRoleId(roleId);
                this.userRoleService.save(ur);
            });
        }
        return count > 0;
    }


//
//    @Override
//    public ApiResult login(String loginAccount, String password) {
//
//        log.debug("进入login方法");
//        // 1 创建UsernamePasswordAuthenticationToken
//        UsernamePasswordAuthenticationToken usernameAuthentication = new UsernamePasswordAuthenticationToken(loginAccount, password);
//        // 2 认证
//        Authentication authentication = this.authenticationManager.authenticate(usernameAuthentication);
//        // 3 保存认证信息
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        // 4 生成自定义token
//        AccessToken accessToken = jwtProvider.createToken((UserDetails) authentication.getPrincipal());
//
//        UserDetail userDetail = (UserDetail) authentication.getPrincipal();
//        // 放入缓存
//        caffeineCache.put(CacheName.USER, userDetail.getUsername(), userDetail);
//        return ApiResult.ok(accessToken);
//    }
//
//    @Override
//    public ApiResult logout() {
//        caffeineCache.remove(CacheName.USER, AuthProvider.getLoginAccount());
//        SecurityContextHolder.clearContext();
//        return ApiResult.ok();
//    }
//
//    @Override
//    public ApiResult refreshToken(String token) {
//        AccessToken accessToken = jwtProvider.refreshToken(token);
//        UserDetail userDetail = caffeineCache.get(CacheName.USER, accessToken.getLoginAccount(), UserDetail.class);
//        caffeineCache.put(CacheName.USER, accessToken.getLoginAccount(), userDetail);
//        return ApiResult.ok(accessToken);
//    }
}
